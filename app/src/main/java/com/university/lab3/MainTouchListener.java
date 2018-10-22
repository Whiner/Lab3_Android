package com.university.lab3;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Toast;

import com.university.lab3.figures.Figure;

import java.util.List;

public class MainTouchListener extends CustomTouchListener {
    private List<Figure> figures;
    private DrawView view;

    public MainTouchListener(Context context, DrawView view) {
        super(context);
        this.view = view;
        this.figures = view.getFigures();

    }

    @Override
    public void onLongTap(MotionEvent e) {
        Figure contextMenuFigure = TapCoordinatesComparator.checkClick((int) e.getX(), (int) e.getY(), figures);
        if (contextMenuFigure != null) {
            Toast.makeText(this.getContext(), "Long Tap on " + contextMenuFigure.toString(), Toast.LENGTH_SHORT).show();
            AlertDialogCreator.createAlertDialog(getContext(), contextMenuFigure, view);
        } else {
            Toast.makeText(getContext(), "Long Tap", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMove(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (view.getMovingFigure() != null) {
            view.getMovingFigure().move((int) (view.getMovingFigure().getX() - distanceX), (int) (view.getMovingFigure().getY() - distanceY));
            view.invalidate();
        }
    }

    @Override
    public void onTouchUp(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        view.setMovingFigure(null);
    }

    @Override
    public void onTouchDown(MotionEvent e) {
        Figure figure = TapCoordinatesComparator.checkClick((int) e.getX(), (int) e.getY(), figures);
        if (figure != null) {
            view.setMovingFigure(figure);
        }
    }

    @Override
    public void onSingleClick(Context context, MotionEvent event) {
        view.setMovingFigure(null);
        Figure figure = TapCoordinatesComparator.checkClick((int) event.getX(), (int) event.getY(), figures);
        if (figure != null) {
            Toast.makeText(context, figure.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDoubleClick(Context context, MotionEvent event) {
        Figure figure = TapCoordinatesComparator.checkClick((int) event.getX(), (int) event.getY(), figures);
        if (figure != null) {
            figures.add(figure.divide(view.getCanvas()));
        }
        view.invalidate();
    }
}
