package com.university.lab3.figures;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.NonNull;

public class Circle extends Figure {

    @Override
    public void draw(Canvas canvas) {
        RectF rect = new RectF(getX(), getY(), getX() + getWidth(), getY() + getHeight());
        canvas.drawOval(rect, getPaint());
    }

    public Circle() {
        super();
    }

    public Circle(int x, int y, int width, int height, int color, int transparency) {
        super(x, y, width, height, color, transparency);
    }

    @NonNull
    @Override
    public String toString() {
        return "Окружность";
    }
}
