package com.example.pract4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity
{
    private final ActivityResultLauncher<Intent> thirdActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->
            {
                if (result.getResultCode() != RESULT_OK) return;

                Intent intent = result.getData();
                if (intent == null) return;
                String date = intent.getStringExtra("ARGUMENT_DATE");
                String time = intent.getStringExtra("ARGUMENT_TIME");

                Toast.makeText(this, "Time successfully transferred", Toast.LENGTH_SHORT).show();

                TextView textDate = findViewById(R.id.text_second_date);
                TextView textTime = findViewById(R.id.text_second_time);
                textDate.setText(date);
                textTime.setText(time);
            });

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

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
        {
            TextView textFirstName = findViewById(R.id.text_second_firstName);
            TextView textLastName = findViewById(R.id.text_second_lastName);

            textFirstName.setText(arguments.getString("ARGUMENT_FIRST_NAME"));
            textLastName.setText(arguments.getString("ARGUMENT_LAST_NAME"));
        }

        Button buttonSubmit = findViewById(R.id.button_second_submit);
        buttonSubmit.setOnClickListener(v ->
        {
            Intent intent = new Intent(v.getContext(), ThirdActivity.class);
            thirdActivityLauncher.launch(intent);
        });
    }
}