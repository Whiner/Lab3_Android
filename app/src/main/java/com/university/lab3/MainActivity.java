package com.university.lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.university.lab3.figures.Circle;
import com.university.lab3.figures.Figure;
import com.university.lab3.figures.Square;
import com.university.lab3.figures.Triangle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Circle circle = new Circle(10, 10, 50, 50, Color.RED, 100);
        Triangle triangle = new Triangle(10, 150, 50, 50, Color.BLUE, 100);
        Square square = new Square(150, 10, 50, 50, Color.GREEN, 100);
        DrawView view = new DrawView(this);
        view.add(circle);
        view.add(triangle);
        view.add(square);
        setContentView(view);
    }

    class DrawView extends View {
        private List<Figure> figures = new ArrayList<>();

        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (Figure figure: figures){
                figure.draw(canvas);
            }
        }

        public void add(Figure figure){
            figures.add(figure);
        }

    }
}
