package com.example.pract4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{

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

        EditText editFirstName = findViewById(R.id.edit_main_firstName);
        EditText editLastName = findViewById(R.id.edit_main_lastName);
        Button buttonNext = findViewById(R.id.button_main_next);

        buttonNext.setOnClickListener(v ->
        {
            Intent intent = new Intent(v.getContext(), SecondActivity.class);
            intent.putExtra("ARGUMENT_FIRST_NAME", editFirstName.getText().toString());
            intent.putExtra("ARGUMENT_LAST_NAME", editLastName.getText().toString());
            startActivity(intent);
        });
    }
}