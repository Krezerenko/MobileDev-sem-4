package com.example.pract10;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    private final String PREFERENCE_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        EditText editUsername = findViewById(R.id.edit_main_username);

        Button buttonSetUsername = findViewById(R.id.button_main_setUsername);
        buttonSetUsername.setOnClickListener(v -> prefs
                .edit()
                .putString(PREFERENCE_USERNAME, editUsername.getText().toString())
                .apply());
        Button buttonDeleteUsername = findViewById(R.id.button_main_deleteUsername);

        buttonDeleteUsername.setOnClickListener(v -> prefs
                .edit()
                .remove(PREFERENCE_USERNAME)
                .apply());
        Button buttonGetUsername = findViewById(R.id.button_main_getUsername);
        TextView textUsername = findViewById(R.id.text_main_username);
        buttonGetUsername.setOnClickListener(v ->
                textUsername.setText(prefs.getString(PREFERENCE_USERNAME, "Не задано")));
    }
}