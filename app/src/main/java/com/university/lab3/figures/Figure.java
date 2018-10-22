package com.university.lab3.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.university.lab3.FigureInstanceDivider;
import com.university.lab3.color.RandomColor;

public abstract class Figure {
    private int x;
    private int y;
    private int width;
    private int height;
    private int color;
    private int transparency;
    private int margin = 5;
    private float sizeFactor;
    private Paint paint = new Paint();

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Figure divide(Canvas canvas) {
        return FigureInstanceDivider.divide(this, canvas.getHeight(), canvas.getWidth());
    }

    public abstract void draw(Canvas canvas);

    public Figure() {
        sizeFactor = 1;
        transparency = 255;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(RandomColor.get());
    }

    public Figure(int x, int y, int width, int height, int color, int transparency) {
        this();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.transparency = transparency;
        paint.setColor(color);
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
        this.paint.setColor(paint.getColor());
        this.paint.setAlpha(paint.getAlpha());
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        this.paint.setColor(color);
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
        this.paint.setAlpha(transparency);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x <= 0) {
            x = 1;
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y <= 0) {
            y = 1;
        }
        this.y = y;
    }

    public float getSizeFactor() {
        return sizeFactor;
    }

    public void setSizeFactor(float sizeFactor) {
        this.sizeFactor = sizeFactor;
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
