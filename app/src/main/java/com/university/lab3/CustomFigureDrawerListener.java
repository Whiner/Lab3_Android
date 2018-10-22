package com.university.lab3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.university.lab3.figures.Circle;
import com.university.lab3.figures.CustomFigure;
import com.university.lab3.figures.Figure;

import java.util.List;

public class CustomFigureDrawerListener extends CustomTouchListener {

    private int brushSize;
    private List<Figure> figures;
    private DrawView view;
    private TouchType lastTouchType = TouchType.UP;

    public CustomFigureDrawerListener(Context context, DrawView view) {
        super(context);
        this.view = view;
        this.figures = view.getFigures();
        this.brushSize = view.getBrushSize();
    }

    @Override
    public void onLongTap(MotionEvent e) {
        Log.i("custom", "longTouch");
    }

    @Override
    public void onMove(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("custom", "touchMove");
        if (view.getMovingFigure() != null) {
            try {
                ((CustomFigure) view.getMovingFigure()).addFigure(
                        new Circle(
                                (int) e2.getX(),
                                (int) e2.getY(),
                                brushSize,
                                brushSize,
                                Color.DKGRAY,
                                255));
                Log.i("_sizes", "x + width: " + (e2.getX() + brushSize));
            } catch (Exception e) {
                Log.e("custom", e.getMessage());
            }
            if (!figures.contains(view.getMovingFigure())) {
                figures.add(view.getMovingFigure());
            }
            lastTouchType = TouchType.MOVE;
            view.invalidate();
        }
    }

    @Override
    public void onTouchUp(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        lastTouchType = TouchType.UP;
        ((CustomFigure) view.getMovingFigure()).createBitmap();
        view.invalidate();
        view.setMovingFigure(null);
        view.setMainTouchListener();
        Log.i("custom", "touchUp");
    }

    @Override
    public void onTouchDown(MotionEvent e) {
        if (lastTouchType == TouchType.MOVE) {
            Log.i("custom", "touchDown, without UP");
            return;
        }
        lastTouchType = TouchType.DOWN;
        view.setMovingFigure(new CustomFigure());
        Log.i("custom", "touchDown");
    }

    @Override
    public void onSingleClick(Context context, MotionEvent event) {
        Log.i("custom", "singleClick");
    }


    @Override
    public void onDoubleClick(Context context, MotionEvent event) {
        Log.i("custom", "doubleClick");
    }
}
