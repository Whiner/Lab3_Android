package com.university.lab3;

import com.university.lab3.figures.Figure;

public class FigureEditor {
    public static void scale(Figure figure, int newWidth, int newHeight) {
        int centerX = figure.getX() + figure.getWidth() / 2;
        int newX = centerX - newWidth / 2;
        int centerY = figure.getY() + figure.getHeight() / 2;
        int newY = centerY - newHeight / 2;
        figure.setX(newX);
        figure.setY(newY);
        figure.setWidth(newWidth);
        figure.setHeight(newHeight);
    }

    public static int convertFromSeekBarToSize(int standardSize, int seekValue) {
        return standardSize / 50 * seekValue;
    }

    public static int convertSizeToSeekBarValue(int standardSize, int figureSize) {
        return figureSize / (standardSize / 50);
    }
}
