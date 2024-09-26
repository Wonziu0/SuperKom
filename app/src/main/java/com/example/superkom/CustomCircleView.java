package com.example.superkom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomCircleView extends View {
    private Paint p;
    private int colorFlag = 1;
    private float radius = 100f;
    private boolean movingRight = true;
    private float circleX;

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setColor(Color.CYAN);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();

        if (circleX == 0) {
            circleX = w / 2;
        }

        canvas.drawCircle(circleX, h / 3, radius, p);
    }

    public void changeColor() {
        int startColor = (colorFlag == 1) ? Color.CYAN : Color.RED;
        int endColor = (colorFlag == 1) ? Color.RED : Color.CYAN;
        ObjectAnimator colorAnimator = ObjectAnimator.ofArgb(p, "color", startColor, endColor);
        colorAnimator.setDuration(500);
        colorAnimator.addUpdateListener(animation -> {
            p.setColor((int) animation.getAnimatedValue());
            invalidate();
        });
        colorAnimator.start();

        colorFlag = (colorFlag == 1) ? 0 : 1;
    }

    public void changeSize() {
        float startRadius = (radius == 100f) ? 100f : 150f;
        float endRadius = (radius == 100f) ? 150f : 100f;
        ObjectAnimator sizeAnimator = ObjectAnimator.ofFloat(this, "radius", startRadius, endRadius);
        sizeAnimator.setDuration(500);
        sizeAnimator.addUpdateListener(animation -> {
            radius = (float) animation.getAnimatedValue();
            invalidate();
        });
        sizeAnimator.start();
    }

    public void moveHorizontally() {
        float startX = movingRight ? circleX : circleX;
        float endX = movingRight ? getWidth() - (radius+75) : radius;

        ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(this, "circleX", startX, endX);
        moveAnimator.setDuration(500);
        moveAnimator.addUpdateListener(animation -> {
            circleX = (float) animation.getAnimatedValue();
            invalidate();
        });
        moveAnimator.start();

        movingRight = !movingRight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            changeColor();
            changeSize();
            moveHorizontally();
            return true;
        }
        return super.onTouchEvent(event);
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public float getCircleX() {
        return circleX;
    }

    public void setCircleX(float circleX) {
        this.circleX = circleX;
        invalidate();
    }
}
