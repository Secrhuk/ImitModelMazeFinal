package com.example.imitmaze;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей Баранов on 01.05.2017.
 */

public class GameManger extends GestureDetector.SimpleOnGestureListener {
    private List<Drawable> drawables = new ArrayList<>();
    private View view;
    private Exit exit;
    private Player player;
    private Maze maze;
    private Rect rect = new Rect();
    private int screenSize;
    final Handler handler = new Handler();
    public GameManger(){

     create(5);

    };

    private void create(int size){    // об'єкти ігрового світу
        drawables.clear();
        maze = new Maze( size);
        drawables.add(maze);
        player = new Player(maze.getStart(),size);
        drawables.add(player);
        exit = new Exit(maze.getEnd(),size);
        drawables.add(exit);
    }



    @Override

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) { // Переміщення при свайпу
     int diffX =0, diffY = 0;
        diffX = Math.round(e2.getX()- e1.getX());   //начальне значення пальця
        diffY = Math.round( e2.getY()- e1.getY());  //кінцеве значення пальця
       // Log.i("GM",String.format("X: %f  Y:%f", diffX, diffY)); // лог тача


///////////////////////////////////////////////////
        if (Math.abs(diffX)>Math.abs(diffY)){
            diffX = diffX>0 ? 1: -1; //переміщення плеєра
            diffY = 0;

        } else {
             diffX = 0;
             diffY = diffY > 0 ? 1 : -1; //переміщення плеєра

         }
///////////////////////////////////////////////////
      /*int cycleStop = 0;
        while (true){
            cycleStop++;
            ///////////////////
            diffX = 0;
            diffY = 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //////////////////
            diffX = 1;
            diffY = 0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //////////////////////
            diffX = 0;
            diffY = -1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            diffX = -1;
            diffY = 0;

            if (cycleStop == 20) break;


     //   diffX = -1;//1 0 -це вправо // -1 0 -це вправо
     //   diffY =0;// 0 1 - це вниз // 0 -1 - це вверх
      /*  try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        boolean stop = false;
        int stepX = player.getX();
        int stepY = player.getY();

        while (maze.canPlayerGoTo(stepX+diffX,stepY+diffY)){
            stepX += diffX;
            stepY += diffY;
            if (diffX != 0){
                if (maze.canPlayerGoTo(stepX,stepY+1)
                    || maze.canPlayerGoTo(stepX,stepY-1)){
                    break;
                }
            }

            if (diffY != 0){
                if (maze.canPlayerGoTo(stepX+1,stepY)
                        || maze.canPlayerGoTo(stepX-1,stepY)){
                    break;
                }
            }
        }
        player.goTo(stepX,stepY);
        if (exit.getPoint().equals(player.getPoint())){ // Обработка собитія вихода з лабіринту


            create(maze.getSize()+10);

        }
        view.invalidate();
        return super.onFling(e1,e2,velocityX,velocityY);
    }

    public void  draw(Canvas canvas){
        for (Drawable drawableItem:
            drawables) {
            drawableItem.draw(canvas,rect);
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setScreenSize(int width, int height) {
        screenSize = Math.min(width,height);
        rect.set(                   //квадрат отцентровки
                (width-screenSize)/2,
                (height-screenSize)/2,
                (width+ screenSize)/2,
                (height+ screenSize)/2
        );
    }

}
