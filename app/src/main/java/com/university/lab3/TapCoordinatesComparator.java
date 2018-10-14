package com.university.lab3;

import com.university.lab3.figures.Figure;

import java.util.List;

public class TapCoordinatesComparator {
    public static Figure checkClick(int x, int y, List<Figure> figures) {
        for (Figure figure : figures) {
            if (x >= figure.getX() && y >= figure.getY()) {
                if (x <= figure.getX() + figure.getWidth() && y <= figure.getY() + figure.getHeight()) {
                    return figure;
                }
            }
        }
        return null;
    }
}
