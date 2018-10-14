package com.university.lab3.figures;

import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.NonNull;

public class Triangle extends Figure {
    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getX() + getWidth() / 2, getY());
        path.lineTo(getX() + getWidth(), getY() + getHeight());
        path.lineTo(getX(), getY() + getHeight());
        path.lineTo(getX() + getWidth() / 2, getY());
        path.close();
        canvas.drawPath(path, getPaint());
    }

    public Triangle() {
        super();
    }

    public Triangle(int x, int y, int width, int height, int color, int transparency) {
        super(x, y, width, height, color, transparency);
    }

    @NonNull
    @Override
    public String toString() {
        return "Треугольник";
    }
}
