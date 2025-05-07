package com.example.pract11;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonRotate = findViewById(R.id.button_third_rotate);
        buttonRotate.setOnClickListener(v ->
                ObjectAnimator
                        .ofFloat(v, "rotation", 0, 120)
                        .setDuration(2000)
                        .start());
        Button buttonStretch = findViewById(R.id.button_third_stretch);
        buttonStretch.setOnClickListener(v ->
        {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1, 2);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1, 3);
            AnimatorSet animations = new AnimatorSet();
            animations.playTogether(scaleX, scaleY);
            animations.setDuration(1000).start();
        });
        Button buttonColor = findViewById(R.id.button_third_color);
        buttonColor.setOnClickListener(v ->
        {
            ObjectAnimator colorAnimator = ObjectAnimator
                    .ofInt(v, "backgroundColor", v.getSolidColor(), Color.GREEN);
            colorAnimator.setEvaluator(new ArgbEvaluator());
            colorAnimator.setDuration(2000).start();
        });
    }
}