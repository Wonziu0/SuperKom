package com.example.superkom;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomRectView extends View{
    private Paint p;
    public CustomRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setColor(Color.GREEN);
    }

    public void onDraw(Canvas canvas){
        int h = getHeight();
        int w = getWidth();
        RectF rect = new RectF(w/2-100, h/8-40, w/2+100, h/6+40);
        canvas.drawRect(rect, p);
    }

}
