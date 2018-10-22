package com.university.lab3;

import com.university.lab3.figures.Circle;
import com.university.lab3.figures.CustomFigure;
import com.university.lab3.figures.Figure;
import com.university.lab3.figures.Square;
import com.university.lab3.figures.Triangle;

public class FigureInstanceDivider {
    private static void choiceDivideCoordinates(Figure oldFigure, Figure newFigure, int canvasHeight, int canvasWidth) {
        if (oldFigure.getX() + oldFigure.getWidth() + oldFigure.getMargin() + oldFigure.getWidth() < canvasWidth) {
            newFigure.setX(oldFigure.getX() + oldFigure.getWidth() + oldFigure.getMargin());
            newFigure.setY(oldFigure.getY());
        } else if (oldFigure.getY() + oldFigure.getHeight() + oldFigure.getMargin() + oldFigure.getHeight() < canvasHeight) {
            newFigure.setX(oldFigure.getX());
            newFigure.setY(oldFigure.getY() + oldFigure.getHeight() + oldFigure.getMargin());
        } else if (oldFigure.getX() - oldFigure.getMargin() - oldFigure.getWidth() > 0) {
            newFigure.setX(oldFigure.getX() - oldFigure.getMargin() - oldFigure.getWidth());
            newFigure.setY(oldFigure.getY());
        } else {
            newFigure.setX(oldFigure.getX());
            newFigure.setY(oldFigure.getY() - oldFigure.getHeight() - oldFigure.getMargin());
        }
    }

    public static Figure divide(Figure figure, int canvasHeight, int canvasWidth) {
        Figure newFigure;
        if (figure instanceof Circle) {
            newFigure = new Circle();
        } else if (figure instanceof Triangle) {
            newFigure = new Triangle();
        } else if (figure instanceof Square) {
            newFigure = new Square();
        } else {
            newFigure = new CustomFigure(((CustomFigure) figure).getBitmap());
        }
        newFigure.setColor(figure.getColor());
        newFigure.setHeight(figure.getHeight());
        newFigure.setWidth(figure.getWidth());
        newFigure.setTransparency(figure.getTransparency());
        newFigure.setPaint(figure.getPaint());
        choiceDivideCoordinates(figure, newFigure, canvasHeight, canvasWidth);
        return newFigure;
    }
}
