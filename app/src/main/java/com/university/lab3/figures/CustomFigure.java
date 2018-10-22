package com.university.lab3.figures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

public class CustomFigure extends Figure {
    private Set<Figure> figures = new HashSet<>();
    private Bitmap bitmap = null;

    public CustomFigure() {
        super();
    }


    public CustomFigure(Bitmap bitmap) {
        super();
        this.bitmap = bitmap.copy(bitmap.getConfig(), true);
    }

    @Override
    public void draw(Canvas canvas) {
        if (bitmap == null) {
            for (Figure figure : figures) {
                figure.draw(canvas);
            }
        } else {
            canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), true), getX(), getY(), getPaint());
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            canvas.drawLine(getX() - 1, getY() - 1, getX() + getWidth() + 1, getY() - 1, paint);
            canvas.drawLine(getX() + getWidth() + 1, getY() - 1, getX() + getWidth() + 1, getY() + getHeight() + 1, paint);
            canvas.drawLine(getX() + getWidth() + 1, getY() + getHeight() + 1, getX() - 1, getY() + getHeight() + 1, paint);
            canvas.drawLine(getX() - 1, getY() + getHeight() + 1, getX() - 1, getY() - 1, paint);

        }
    }

    public void addFigure(Figure figure) throws Exception {
        if (bitmap == null) {
            if (figures.isEmpty()) {
                setWidth(figure.getWidth());
                setHeight(figure.getHeight());
                setX(figure.getX());
                setY(figure.getY());
            }
            figures.add(figure);
        } else {
            throw new Exception("Невозможно добавить фигуру после преобразования в Bitmap");
        }
    }

    public void recalcCoords(Figure newFigure) {
        if (newFigure.getX() < getX()) {
            setX(newFigure.getX());
        }
        if (newFigure.getY() < getY()) {
            setY(newFigure.getY());
        }
        if (newFigure.getX() + newFigure.getWidth() > getX() + getWidth()) {
            setWidth(newFigure.getX() + newFigure.getWidth() - getX());
        }
        if (newFigure.getY() + newFigure.getHeight() > getY() + getHeight()) {
            setHeight(newFigure.getY() + newFigure.getHeight() - getY());
        }
    }

    public void recalcCoords() {
        for (Figure figure : figures) {
            recalcCoords(figure);
        }
    }

    public void createBitmap() {
        if (!figures.isEmpty()) {
            recalcCoords();
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            drawOnBitmap(bitmap);
        }
    }

    private void drawOnBitmap(Bitmap bitmap) {
        int lastX;
        int lastY;
        Canvas bitmapCanvas = new Canvas(bitmap);
        for (Figure figure : figures) {
            lastX = figure.getX();
            lastY = figure.getY();

            figure.setX(lastX - getX());
            figure.setY(lastY - getY());

            figure.draw(bitmapCanvas);

            figure.setX(lastX);
            figure.setY(lastY);
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @NonNull
    @Override
    public String toString() {
        return "Произвольная фигура";
    }
}
