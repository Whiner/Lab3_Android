package com.university.lab3;

import android.util.Log;

import com.university.lab3.figures.CustomFigure;

public class CustomFigureDrawer {
    public static void addPixels(CustomFigure figure, int brashRadius, int x, int y) { // переделать под рисование кругами
        for (int i = 0; i < 360; i++) {
            for (int j = 1; j < brashRadius; j++) {
                double rad = (double) i / 180 * 3.14;
                int _x = (int) (j * Math.cos(rad)) + x;
                int _y = (int) (j * Math.sin(rad)) + y;
                Log.i("custom", "(" + _x + ";" + _y + ")");
                //figure.addPixel(_x, _y);
            }
        }
    }
}
