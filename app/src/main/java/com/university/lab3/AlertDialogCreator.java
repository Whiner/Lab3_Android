package com.university.lab3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.university.lab3.figures.Figure;

public class AlertDialogCreator {
    private static final int maxSeekBarValue = 255;

    public static void createAlertDialog(final Context context, final Figure contextMenuFigure, final DrawView view) {
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
                                                view.invalidate();
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
                                        (int) ((contextMenuFigure.getSizeFactor() - 0.5) * maxSeekBarValue),
                                        new SeekBar.OnSeekBarChangeListener() {
                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                float sizeFactor = FigureEditor.getSizeFactor(i);
                                                FigureEditor.scale(
                                                        contextMenuFigure,
                                                        (int) (contextMenuFigure.getWidth() / contextMenuFigure.getSizeFactor() * sizeFactor + 0.5),
                                                        (int) (contextMenuFigure.getHeight() / contextMenuFigure.getSizeFactor() * sizeFactor + 0.5)
                                                );
                                                contextMenuFigure.setSizeFactor(sizeFactor);
                                                view.invalidate();
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
                                int seekBarValue = (int) ((contextMenuFigure.getSizeFactor() - 0.5) * maxSeekBarValue);
                                seekBar = createSeekBar(context,
                                        (seekBarValue + contextMenuFigure.getTransparency()) / 2,
                                        new SeekBar.OnSeekBarChangeListener() {
                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                                                float sizeFactor = FigureEditor.getSizeFactor(i);
                                                FigureEditor.scale(
                                                        contextMenuFigure,
                                                        (int) (contextMenuFigure.getWidth() / contextMenuFigure.getSizeFactor() * sizeFactor + 0.5),
                                                        (int) (contextMenuFigure.getHeight() / contextMenuFigure.getSizeFactor() * sizeFactor + 0.5)
                                                );
                                                contextMenuFigure.setSizeFactor(sizeFactor);
                                                contextMenuFigure.setTransparency(i);
                                                view.invalidate();
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

    private static SeekBar createSeekBar(Context context,
                                         int currentValue,
                                         SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        SeekBar seekBar = new SeekBar(context);
        seekBar.setMax(maxSeekBarValue);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar.setProgress(currentValue);
        seekBar.setKeyProgressIncrement(1);
        return seekBar;
    }
}
