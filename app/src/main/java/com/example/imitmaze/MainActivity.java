package com.example.imitmaze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity {

    private  MazeView view;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameManger gameManger = new GameManger();
        view = new MazeView(this,gameManger);
        setContentView(view);



       gestureDetector = new GestureDetector(this,gameManger); //розпізнавання жестів

    }

    private void setContentView() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       return  gestureDetector.onTouchEvent(event);
    }
}
