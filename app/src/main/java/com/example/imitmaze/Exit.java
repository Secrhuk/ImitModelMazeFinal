package com.example.imitmaze;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Сергей Баранов on 02.05.2017.
 */

public class Exit extends Dot {
    public Exit(Point point, int size) {
        super(point, getPaint(), size);
    }
    private static Paint getPaint(){
        Paint paint= new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        return paint;
    }
}
