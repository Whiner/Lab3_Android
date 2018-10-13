package com.university.lab3.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Figure {

    @Override
    public void draw(Canvas canvas) {
        canvas.drawOval(getX(), getY(), getX() + getWidth(), getY() + getHeight(), getPaint());
    }

    public Circle() {
    }

    public Circle(int x, int y, int width, int height, int color, int transparency) {
        super(x, y, width, height, color, transparency);
    }
}
