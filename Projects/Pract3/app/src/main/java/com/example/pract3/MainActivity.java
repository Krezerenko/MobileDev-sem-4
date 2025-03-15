package com.example.pract3;

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

        Button b = findViewById(R.id.button_main_next);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String firstName = ((EditText)findViewById(R.id.edit_main_firstName)).getText().toString();
                String secondName = ((EditText)findViewById(R.id.edit_main_secondName)).getText().toString();
                String thirdName = ((EditText)findViewById(R.id.edit_main_thirdName)).getText().toString();
                User u = new User(firstName, secondName, thirdName);

                Intent i = new Intent(v.getContext(), SecondActivity.class);
                i.putExtra("user", u);
                startActivity(i);
            }
        });
    }
}