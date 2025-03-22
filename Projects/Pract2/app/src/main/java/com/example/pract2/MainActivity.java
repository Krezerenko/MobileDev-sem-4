package com.example.pract2;

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
    private static final String TAG = "MainActivity";

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

        Button button = findViewById(R.id.programmedButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                moveToSecondPage(v);
            }
        });
    }

    public void moveToSecondPage(View v)
    {
        EditText nameField = findViewById(R.id.nameField);
        EditText groupField = findViewById(R.id.groupField);
        EditText ageField = findViewById(R.id.ageField);
        EditText gradeField = findViewById(R.id.gradeField);

        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("name", nameField.getText().toString());
        i.putExtra("group", groupField.getText().toString());
        i.putExtra("age", ageField.getText().toString());
        i.putExtra("grade", gradeField.getText().toString());

        startActivity(i);
    }
}