package com.example.imitmaze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.params.BlackLevelPattern;

/**
 * Created by Сергей Баранов on 02.05.2017.
 */

public class Dot implements Drawable {
    private  int size;
    protected Point point;
    protected Paint paint;
    protected int userStep;


    public  Dot(Point point, Paint paint,int size){
        this.size = size;
        this.point = point;
        this.paint = paint;

    }


    public Point getPoint(){
        return point;
    }
    @Override
    public void draw(Canvas canvas, Rect rect) {
        userStep++;
        paint.setTextSize(60);
        canvas.drawText("Users steps = "+(userStep-1),10,150,paint);
        float cellSize = (float)(rect.right - rect.left)/size;
        canvas.drawRect(
                rect.left+point.x*cellSize,
                rect.top+point.y*cellSize ,
                rect.left+ point.x* cellSize + cellSize ,
                rect.top+ point.y * cellSize + cellSize,
                paint);

    }
}
