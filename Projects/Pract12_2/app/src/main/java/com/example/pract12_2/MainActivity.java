package com.example.pract12_2;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity
{
    private final Uri CONTENT_URI = Uri.parse("content://com.example.fileprovider/files/student");
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

        TextView textName = findViewById(R.id.text_main_name);
        TextView textSurname = findViewById(R.id.text_main_surname);
        TextView textGroup = findViewById(R.id.text_main_group);
        TextView textAge = findViewById(R.id.text_main_age);

        Button buttonRead = findViewById(R.id.button_main_read);

        buttonRead.setOnClickListener(v ->
        {
            ContentResolver resolver = getContentResolver();
            try (InputStream in = resolver.openInputStream(CONTENT_URI))
            {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in, StandardCharsets.UTF_8));
                String json = reader.lines().collect(Collectors.joining("\n"));
                Gson gson = new Gson();
                Student student = gson.fromJson(json, Student.class);
                textName.setText(student.getName());
                textSurname.setText(student.getSurname());
                textGroup.setText(student.getGroup());
                textAge.setText(String.valueOf(student.getAge()));
            }
            catch (IOException e)
            {
                Toast.makeText(this, "Не удалось прочитать файл",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}