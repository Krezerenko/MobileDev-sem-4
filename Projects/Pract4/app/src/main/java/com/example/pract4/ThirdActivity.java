package com.example.pract4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        EditText editDate = findViewById(R.id.edit_third_date);
        EditText editTime = findViewById(R.id.edit_third_time);
        Button buttonOk = findViewById(R.id.button_third_ok);

        buttonOk.setOnClickListener(v ->
        {
            Intent result = new Intent();
            result.putExtra("ARGUMENT_DATE", editDate.getText().toString());
            result.putExtra("ARGUMENT_TIME", editTime.getText().toString());

            setResult(RESULT_OK, result);
            finish();
        });
    }
}