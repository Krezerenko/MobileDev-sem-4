package com.example.pract9_2;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

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

        EditText editFileName = findViewById(R.id.edit_main_name);

        TextView textContents = findViewById(R.id.text_main_contents);
        Button buttonRead = findViewById(R.id.button_main_read);
        buttonRead.setOnClickListener(v -> textContents.setText(
                readFile(editFileName.getText().toString())));
    }

    private String readFile(String fileName)
    {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(dir, fileName);
        if (!file.exists())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Файл не найден")
                    .setPositiveButton("Ок", (dialog, which) -> { })
                    .show();
            return "";
        }
        try (FileInputStream in = new FileInputStream(file))
        {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8));
            return reader.lines().collect(Collectors.joining("\n"));
        }
        catch (IOException e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Не удалось прочитать файл")
                    .setPositiveButton("Ок", (dialog, which) -> { })
                    .show();
            return "";
        }
    }
}