package com.university.lab3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.university.lab3.figures.Figure;

import java.util.ArrayList;
import java.util.List;

class DrawView extends View {
    private List<Figure> figures = new ArrayList<>();
    private Canvas canvas;
    private Figure movingFigure;
    private Figure contextMenuFigure;

    public DrawView(final Context context) {
        super(context);
        final View thisView = this;
        this.setOnTouchListener(new CustomTouchListener(context) {

            @Override
            public void onLongTap(MotionEvent e) {
                contextMenuFigure = TapCoordinatesComparator.checkClick((int) e.getX(), (int) e.getY(), figures); // проверка на нот нулл
                if (contextMenuFigure != null) {
                    Toast.makeText(context, "Long Tap on " + contextMenuFigure.toString(), Toast.LENGTH_SHORT).show();
                    createAlertDialog(context);
                } else {
                    Toast.makeText(context, "Long Tap", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMove(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (movingFigure != null) {
                    movingFigure.move((int) (movingFigure.getX() - distanceX), (int) (movingFigure.getY() - distanceY));
                    invalidate();
                }
            }

            @Override
            public void onTouchUp(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                movingFigure = null;
            }

            @Override
            public void onTouchDown(MotionEvent e) {
                Figure figure = TapCoordinatesComparator.checkClick((int) e.getX(), (int) e.getY(), figures);
                if (figure != null) {
                    movingFigure = figure;
                }
            }

            @Override
            public void onSingleClick(Context context, MotionEvent event) {
                movingFigure = null;
                Figure figure = TapCoordinatesComparator.checkClick((int) event.getX(), (int) event.getY(), figures);
                if (figure != null) {
                    Toast.makeText(context, figure.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDoubleClick(Context context, MotionEvent event) {
                Figure figure = TapCoordinatesComparator.checkClick((int) event.getX(), (int) event.getY(), figures);
                if (figure != null) {
                    figures.add(figure.divide(canvas));
                }
                invalidate();
            }
        });

    }

    //Toast.makeText(context, "Menu item " + i, Toast.LENGTH_SHORT).show();
    private void createAlertDialog(final Context context) {
        new AlertDialog.Builder(context).setItems(
                new CharSequence[]{"Прозрачность", "Масштаб", "Комбинированное преобразвоание"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setView(findViewById(R.id.dialog)); // не работает

                                builder.create().show();
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            default:
                                Toast.makeText(context, "Menu item " + i, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create().show();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        redraw();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void redraw() {
        for (Figure figure : figures) {
            figure.draw(canvas);
        }
    }

    public void add(Figure figure) {
        figures.add(figure);
        if (canvas != null) {
            redraw();
            invalidate();
        }
    }

    public void clear() {
        figures.clear();
        invalidate();
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public Figure getContextMenuFigure() {
        return contextMenuFigure;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}