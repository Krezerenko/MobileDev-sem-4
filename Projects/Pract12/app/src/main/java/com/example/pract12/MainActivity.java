package com.example.pract12;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

        EditText editName = findViewById(R.id.edit_main_name);
        EditText editSurname = findViewById(R.id.edit_main_surname);
        EditText editGroup = findViewById(R.id.edit_main_group);
        EditText editAge = findViewById(R.id.edit_main_age);

        Button buttonWrite = findViewById(R.id.button_main_write);
        buttonWrite.setOnClickListener(v ->
        {
            Gson parser = new Gson();
            Student student = new Student(
                    editName.getText().toString(),
                    editSurname.getText().toString(),
                    editGroup.getText().toString(),
                    Integer.parseInt(editAge.getText().toString()));
            try (FileOutputStream out = openFileOutput("student", MODE_PRIVATE))
            {
                String json = parser.toJson(student);
                out.write(json.getBytes(StandardCharsets.UTF_8));
            }
            catch (IOException e)
            {
                Toast.makeText(this, "Не удалось записать данные в файл",
                        Toast.LENGTH_SHORT).show();
            }
            File file = getFileStreamPath("student");
            Uri uri = FileProvider.getUriForFile(this, "com.example.fileprovider", file);
            grantUriPermission("com.example.pract12_2", uri, FLAG_GRANT_READ_URI_PERMISSION);
        });
    }
}