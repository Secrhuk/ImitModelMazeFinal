package com.example.imitmaze;  // Єдиний інтерфейс відображення на екрані


import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Сергей Баранов on 01.05.2017.
 */

interface Drawable {
    void draw(Canvas canvas, Rect rect);

}
