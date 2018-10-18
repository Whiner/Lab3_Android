package com.university.lab3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.university.lab3.figures.Figure;

import java.util.ArrayList;
import java.util.List;

class DrawView extends View {
    private List<Figure> figures = new ArrayList<>();
    private Canvas canvas;
    private Figure movingFigure;
    private Figure contextMenuFigure;
    private final int figuresSize = 150;

    public DrawView(final Context context) {
        super(context);
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
                new CharSequence[]{"Прозрачность", "Масштаб", "Комбинированное преобразование"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        View seekBar = null;
                        switch (i) {
                            case 0:
                                seekBar = createSeekBar(
                                        context,
                                        contextMenuFigure.getTransparency(),
                                        new SeekBar.OnSeekBarChangeListener() {
                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                contextMenuFigure.setTransparency(i);
                                                invalidate();
                                            }

                                            @Override
                                            public void onStartTrackingTouch(SeekBar seekBar) {
                                            }

                                            @Override
                                            public void onStopTrackingTouch(SeekBar seekBar) {
                                            }

                                        });
                                builder.setTitle("Прозрачность");
                                break;
                            case 1:
                                seekBar = createSeekBar(
                                        context,
                                        FigureEditor.convertSizeToSeekBarValue(
                                                figuresSize,
                                                (contextMenuFigure.getHeight() + contextMenuFigure.getWidth()) / 2
                                        ),
                                        new SeekBar.OnSeekBarChangeListener() {
                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                int size = FigureEditor.convertFromSeekBarToSize(figuresSize, i);
                                                FigureEditor.scale(
                                                        contextMenuFigure,
                                                        size,
                                                        size);
                                                invalidate();
                                            }

                                            @Override
                                            public void onStartTrackingTouch(SeekBar seekBar) {
                                            }

                                            @Override
                                            public void onStopTrackingTouch(SeekBar seekBar) {
                                            }

                                        });
                                builder.setTitle("Масштаб");
                                break;
                            case 2:
                                int seekBarValue = FigureEditor.convertSizeToSeekBarValue(
                                        figuresSize,
                                        (contextMenuFigure.getHeight() + contextMenuFigure.getWidth()) / 2
                                );
                                seekBar = createSeekBar(context,
                                        (seekBarValue + contextMenuFigure.getTransparency()) / 2,
                                        new SeekBar.OnSeekBarChangeListener() {
                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                int size = FigureEditor.convertFromSeekBarToSize(figuresSize, i);
                                                FigureEditor.scale(
                                                        contextMenuFigure,
                                                        size,
                                                        size);
                                                contextMenuFigure.setTransparency(i);
                                                invalidate();
                                            }

                                            @Override
                                            public void onStartTrackingTouch(SeekBar seekBar) {

                                            }

                                            @Override
                                            public void onStopTrackingTouch(SeekBar seekBar) {

                                            }
                                        }
                                );
                                builder.setTitle("Комбинация");
                                break;
                            default:
                                Toast.makeText(context, "Menu item " + i, Toast.LENGTH_SHORT).show();
                        }
                        builder.setView(seekBar);
                        builder.create().show();
                    }
                }).create().show();
    }


    private SeekBar createSeekBar(Context context,
                                  int currentValue,
                                  SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        SeekBar seekBar = new SeekBar(context);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar.setProgress(currentValue);
        seekBar.setKeyProgressIncrement(1);
        return seekBar;
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

    public int getFiguresSize() {
        return figuresSize;
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