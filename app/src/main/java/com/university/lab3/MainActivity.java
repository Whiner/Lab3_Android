package com.university.lab3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.university.lab3.figures.Circle;
import com.university.lab3.figures.Square;
import com.university.lab3.figures.Triangle;

public class MainActivity extends AppCompatActivity {

    private DrawView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new DrawView(this);
        setContentView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void firstOnClick(MenuItem item) {
        view.add(new Square(450, 10, view.getFiguresSize(), view.getFiguresSize(), Color.GREEN, 255));
    }

    public void secondOnClick(MenuItem item) {
        view.add(new Circle(10, 10, view.getFiguresSize(), view.getFiguresSize(), Color.RED, 255));
    }

    public void thirdOnClick(MenuItem item) {
        view.add(new Triangle(10, 450, view.getFiguresSize(), view.getFiguresSize(), Color.BLUE, 255));
    }

    public void fourthOnClick(MenuItem item) {
        view.drawCustomFigure();
    }

    public void fifthOnClick(MenuItem item) {
        view.clear();
    }

    public void sixthOnClick(MenuItem item) {
    }
}
