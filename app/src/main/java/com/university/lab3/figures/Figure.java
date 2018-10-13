package com.university.lab3.figures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.EventListener;

public abstract class Figure {
    private int x;
    private int y;
    private int width;
    private int height;
    private int color;
    private int transparency;
    private int margin = 5;
    private Paint paint = new Paint();

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Figure divide(Canvas canvas) throws InstantiationException, IllegalAccessException {
        Figure newFigure = this.getClass().newInstance();
        newFigure.setColor(color);
        newFigure.setHeight(height);
        newFigure.setWidth(width);
        newFigure.setTransparency(transparency);
        if(x + width + margin + width < canvas.getMaximumBitmapWidth()){
            newFigure.setX(x + width + margin);
            newFigure.setY(y);
        } else if(y + height + margin + height < canvas.getMaximumBitmapHeight()){
            newFigure.setX(x);
            newFigure.setY(y + height + margin);
        } else if(x - width - margin - width > 0){
            newFigure.setX(x - width - margin - width);
            newFigure.setY(y);
        } else {
            newFigure.setX(x);
            newFigure.setY(y - height - margin - height);
        }
        newFigure.draw(canvas);
        return newFigure;
    }

    public abstract void draw(Canvas canvas);

    public Figure() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    public Figure(int x, int y, int width, int height, int color, int transparency) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.transparency = transparency;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setAlpha(transparency);
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
