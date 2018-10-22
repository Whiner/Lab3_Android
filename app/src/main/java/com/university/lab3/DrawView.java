package com.university.lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.university.lab3.figures.Figure;

import java.util.ArrayList;
import java.util.List;

class DrawView extends View {
    private List<Figure> figures = new ArrayList<>();
    private Canvas canvas;
    private Figure movingFigure;
    private final int brushSize = 50;
    private final int figuresSize = 150;

    public DrawView(final Context context) {
        super(context);
        setMainTouchListener();
    }

    public void setMainTouchListener() {
        this.setOnTouchListener(new MainTouchListener(getContext(), this));
    }

    public void drawCustomFigure(int color) {
        this.setOnTouchListener(new CustomFigureDrawerListener(getContext(), this, color));
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

    public int getFiguresSize() {
        return figuresSize;
    }

    public void redraw() {
        for (Figure figure : figures) {
            figure.draw(canvas);
        }
    }

    public void add(Figure figure) {
        figures.add(figure);
        if (canvas != null) {
            invalidate();
        }
    }

    public void clear() {
        figures.clear();
        invalidate();
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Figure getMovingFigure() {
        return movingFigure;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setMovingFigure(Figure movingFigure) {
        this.movingFigure = movingFigure;
    }
}