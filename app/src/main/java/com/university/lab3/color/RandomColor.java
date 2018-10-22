package com.university.lab3.color;

import android.graphics.Color;

import java.util.Random;

public class RandomColor {
    public static int get() {
        Random random = new Random();
        return Color.rgb(random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));

    }
}
