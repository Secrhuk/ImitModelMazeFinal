package com.example.imitmaze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/* Нумерація
X   Y
    0 1 2 3 4 5 6 7 8 9
* 0|- - - - - - - - - - - -
* 1|
* 2|
* 3|
* 4|
* 5|
* */
public class Maze implements Drawable {
    private Paint wallPaint;
    private final boolean[][] array;

    public int getSize() {
        return size;
    }

    private  final int size;
    private final Point end = new Point(1,1); //точка входа
    private  int bestScore = 0;
    private  Point start;


    public  Maze(int size){
        wallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wallPaint.setColor(Color.BLUE);
        this.size = size;
        array = new boolean[size][size];
        generateMaze();
    } ;

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
    private void generateMaze(){ //ствоюю лабыринт
        for (int i =0; i<size; ++i) {
            for (int j = 0; j < size; ++j) {

                array[i][j] = i % 2 != 0 && j%2 !=0
                && i < size-1 && j < size-1;
            }
        }
        Random random = new Random();
        Stack<Point> stack = new Stack<>();
        stack.push(end);
        while (stack.size()>0){
            Point current = stack.peek();
            List<Point>unusedNeighbors = new LinkedList<>();
            //Лівий сусід + шаг влыво
            if (current.x >2 ){
                if (!isUsedCell(current.x-2,current.y)){
                    unusedNeighbors.add(new Point(current.x-2,current.y));
                }
            }
            //вверх сусід + шаг вверх

            if (current.y >2 ){
                if (!isUsedCell(current.x,current.y-2)){
                    unusedNeighbors.add(new Point(current.x ,current.y-2));
                }
            }

            //  сусід + шаг вправо
            if (current.x < size-2){
                if (!isUsedCell(current.x+2,current.y)){
                    unusedNeighbors.add(new Point(current.x+2,current.y));
                }
            }

            //  сусід + шаг вниз
            if (current.y < size-2){
                if (!isUsedCell(current.x,current.y+2)){
                    unusedNeighbors.add(new Point(current.x ,current.y+2));
                }
            }

            if (unusedNeighbors.size() >0){
              int rnd = random.nextInt(unusedNeighbors.size());// рандомний лабіринт
               Point direction = unusedNeighbors.get(rnd);


                int diffX = (direction.x - current.x) /2;
                int diffY = (direction.y - current.y)/2;
                array[current.y+ diffY][current.x+diffX] = true; //Убрать стенку між клітинкою в яку треба піти
                stack.push(direction);

            } else {
                if (bestScore < stack.size()){
                    bestScore = stack.size();
                    start = current;
                }
                stack.pop();
            }

            /////////
        }

    }

    public  boolean isCrossroad(int x,int y){
        return  isUsedCell(x, y);
    }

   public boolean canPlayerGoTo(int x,int y){
       return  array[y][x];
   }

    private  boolean isUsedCell(int x, int y){  // клітинка має прохід і не є стіною
        if (x<0 || y<0 ||x>=size-1 || y> size-1){
                return  true;
        }
        return array[y-1][x] //left
        || array[y][x-1] //top
        || array [y+1][x] //right
        || array [y][x+1]; //bottom;
    };
    @Override
    public void draw(Canvas canvas, Rect rect) {

        wallPaint.setTextSize(60);
        canvas.drawText("Maze difficulty = "+size,10,50,wallPaint);
        float cellSize = (float)(rect.right - rect.left)/size;
        for (int i =0; i<size;++i) {
            for (int j=0;j<size;++j){
                if (!array[i][j]){
                    float left = j*cellSize+rect.left;
                    float top = i*cellSize+rect.top;
                    canvas.drawRect(
                            left,
                            top,
                            left + cellSize,
                            top  + cellSize,
                            wallPaint
                    );
                }
            }
        }
    }


}

