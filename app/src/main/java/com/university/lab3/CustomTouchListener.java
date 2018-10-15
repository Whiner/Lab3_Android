package com.university.lab3;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public abstract class CustomTouchListener implements View.OnTouchListener {
    private Context _context;


    public CustomTouchListener(final Context context) {
        this._context = context;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                onDoubleClick(_context, e);
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                onSingleClick(_context, e);
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                onTouchDown(e);
                return super.onDown(e);
            }


            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                onTouchUp(e1, e2, velocityX, velocityY);
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                onMove(e1, e2, distanceX, distanceY);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                onLongTap(e);
                super.onLongPress(e);
            }

        });
    }

    private GestureDetector gestureDetector;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this._context = v.getContext();
        gestureDetector.onTouchEvent(event);
        return true;

    }

    public abstract void onLongTap(MotionEvent e);

    public abstract void onMove(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);

    public abstract void onTouchUp(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);

    public abstract void onTouchDown(MotionEvent e);

    public abstract void onSingleClick(Context context, MotionEvent event);

    public abstract void onDoubleClick(Context context, MotionEvent event);
}


/*public class CustomDoubleClickListener extends GestureDetector.SimpleOnGestureListener {
    private List<Figure> figures;
    private Canvas canvas;

    public CustomDoubleClickListener(List<Figure> figures, Canvas canvas) {
        this.figures = figures;
        this.canvas = canvas;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Figure figure = checkClick((int) e.getX(), (int) e.getY());
        if(figure != null){
            try {
                figure.divide(canvas);
                return true;
            } catch (InstantiationException | IllegalAccessException e1) {
                e1.printStackTrace();

            }
        }
        return false;

    }

    private Figure checkClick(int x, int y){
        for (Figure figure: figures){
            if(x >= figure.getX() && y >= figure.getY()){
                if(x <= figure.getX() + figure.getWidth() && y <= figure.getY() + figure.getHeight()){
                    return figure;
                }
            }
        }
        return null;
    }
}*/
