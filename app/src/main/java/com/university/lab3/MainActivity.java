package com.university.lab3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.university.lab3.figures.Circle;
import com.university.lab3.figures.Square;
import com.university.lab3.figures.Triangle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Circle circle = new Circle(10, 10, 150, 150, Color.RED, 100);
        Triangle triangle = new Triangle(10, 450, 150, 150, Color.BLUE, 100);
        Square square = new Square(450, 10, 150, 150, Color.GREEN, 100);
        DrawView view = new DrawView(this);
        view.add(circle);
        view.add(triangle);
        view.add(square);
        setContentView(view);

    }


}
