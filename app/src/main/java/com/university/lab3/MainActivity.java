package com.university.lab3;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.university.lab3.color.RandomColor;
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
        view.add(new Square(10, 10, view.getFiguresSize(), view.getFiguresSize(), RandomColor.get(), 255));
    }

    public void secondOnClick(MenuItem item) {
        view.add(new Circle(10, 10, view.getFiguresSize(), view.getFiguresSize(), RandomColor.get(), 255));
    }

    public void thirdOnClick(MenuItem item) {
        view.add(new Triangle(10, 10, view.getFiguresSize(), view.getFiguresSize(), RandomColor.get(), 255));
    }

    public void fourthOnClick(MenuItem item) {
        view.drawCustomFigure(RandomColor.get());
    }

    public void fifthOnClick(MenuItem item) {
        view.clear();
    }

    public void sixthOnClick(MenuItem item) {
        boolean save = CanvasSaver.save(view);
        new AlertDialog
                .Builder(view.getContext())
                .setMessage("Сохранено: " + save).show();
    }
}
