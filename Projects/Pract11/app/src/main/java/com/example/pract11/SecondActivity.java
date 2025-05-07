package com.example.pract11;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity
{
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        player = new MediaPlayer();
        try {
            player.setDataSource("https://www.myinstants.com/media/sounds/perduliatsiia.mp3");
            player.prepare();
        }
        catch (IOException e) {
            Toast.makeText(this, "Не удалось получить звук", Toast.LENGTH_SHORT).show();
        }

        Button buttonPlay = findViewById(R.id.button_second_play);
        buttonPlay.setOnClickListener(v -> {
            if (player.isPlaying()) player.pause();
            else player.start();
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        player.release();
    }
}