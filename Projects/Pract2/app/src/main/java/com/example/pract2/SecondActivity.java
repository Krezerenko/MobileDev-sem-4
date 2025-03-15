package com.example.pract2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity
{

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

        Bundle args = getIntent().getExtras();
        if (args == null) return;

        String name = args.getString("name");
        String group = args.getString("group");
        String age = args.getString("age");
        String grade = args.getString("grade");

        TextView nameOut = findViewById(R.id.nameOut);
        TextView groupOut = findViewById(R.id.groupOut);
        TextView ageOut = findViewById(R.id.ageOut);
        TextView gradeOut = findViewById(R.id.gradeOut);

        nameOut.setText(name);
        groupOut.setText(group);
        ageOut.setText(age);
        gradeOut.setText(grade);
    }
}