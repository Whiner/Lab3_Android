package com.university.lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.university.lab3.figures.Figure;

import java.util.ArrayList;
import java.util.List;

class DrawView extends View {
    private List<Figure> figures = new ArrayList<>();
    private Context context;
    private Canvas canvas;
    private Figure movingFigure;

    public DrawView(Context context) {
        super(context);
        this.context = context;
        this.setOnTouchListener(new CustomTouchListener(context) {

            @Override
            public void onMove(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
               /* Log.i("coords", "e1: x(" + e1.getX() + ") y(" + e1.getY() + ") " +
                        "e2 :" + e1.getX() + ") y(" + e1.getY() + ") " +
                        "distX(" + distanceX + ") " +
                        "distY(" + distanceY + ")");*/
                if (movingFigure != null) {
                    movingFigure.move((int) (movingFigure.getX() - distanceX), (int) (movingFigure.getY() - distanceY));
                    invalidate();
                }
            }

            @Override
            public void onTouchUp(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                movingFigure = null;
                Log.i("custom", "UP");
            }

            @Override
            public void onTouchDown(MotionEvent e) {
                Figure figure = TapCoordinatesComparator.checkClick((int) e.getX(), (int) e.getY(), figures);
                if (figure != null) {
                    movingFigure = figure;
                }
            }

            @Override
            public void onSingleClick(Context context, MotionEvent event) {
                Figure figure = TapCoordinatesComparator.checkClick((int) event.getX(), (int) event.getY(), figures);
                if (figure != null) {
                    Toast.makeText(context, figure.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDoubleClick(Context context, MotionEvent event) {
                Figure figure = TapCoordinatesComparator.checkClick((int) event.getX(), (int) event.getY(), figures);
                if (figure != null) {
                    figures.add(figure.divide(canvas));
                }
                invalidate();
            }
        });

    }


    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        redraw();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void redraw() {
        for (Figure figure : figures) {
            figure.draw(canvas);
        }
    }

    public void add(Figure figure) {
        figures.add(figure);
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }
}