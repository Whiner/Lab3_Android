package com.university.lab3.figures;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

public class Square extends Figure {

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), getPaint());
    }

    public Square() {
        super();
    }

    public Square(int x, int y, int width, int height, int color, int transparency) {
        super(x, y, width, height, color, transparency);
    }

    @NonNull
    @Override
    public String toString() {
        return "Квадрат";
    }
}
