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
    private int size = 150;
    private final String transparencyItem = "Прозрачность";
    private final String sizeItem = "Размер";
    private final String comboItem = "Комбинированное преобразование";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new DrawView(this);
        setContentView(view);
        //registerForContextMenu(view);
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //if(view.getContextMenuFigure() != null) {
        Log.i("custom", "onCreateContextMenu");
        menu.add(transparencyItem);
        menu.add(sizeItem);
        menu.add(comboItem);
        //}
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.i("custom", "onContextItemSelected");
        switch (item.getTitle().toString()) {
            case comboItem:
                break;
            case sizeItem:
                break;
            case transparencyItem:
                Dialog dialog = new Dialog(this);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                View layout = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dialog));
                dialog.setContentView(layout);
                break;
        }
        return super.onContextItemSelected(item);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void firstOnClick(MenuItem item) {
        view.add(new Square(450, 10, size, size, Color.GREEN, 100));
    }

    public void secondOnClick(MenuItem item) {
        view.add(new Circle(10, 10, size, size, Color.RED, 100));
    }

    public void thirdOnClick(MenuItem item) {
        view.add(new Triangle(10, 450, 150, 150, Color.BLUE, 100));
    }

    public void fourthOnClick(MenuItem item) {
    }

    public void fifthOnClick(MenuItem item) {
        view.clear();
    }
}
