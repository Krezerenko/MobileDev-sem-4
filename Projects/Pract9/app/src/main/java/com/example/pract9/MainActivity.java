package com.example.pract9;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        EditText editFileContents = findViewById(R.id.edit_main_contents);

        Button buttonCreate = findViewById(R.id.button_main_create);
        buttonCreate.setOnClickListener(v -> createFile(editFileName.getText().toString(),
                editFileContents.getText().toString().getBytes(StandardCharsets.UTF_8)));

        TextView textContents = findViewById(R.id.text_main_contents);
        Button buttonRead = findViewById(R.id.button_main_read);
        buttonRead.setOnClickListener(v -> textContents.setText(
                readFile(editFileName.getText().toString())));

        Button buttonDelete = findViewById(R.id.button_main_delete);
        buttonDelete.setOnClickListener(v -> deleteFileAlert(editFileName.getText().toString()));

        Button buttonAppend = findViewById(R.id.button_main_append);
        buttonAppend.setOnClickListener(v -> appendToFile(editFileName.getText().toString(),
                editFileContents.getText().toString().getBytes(StandardCharsets.UTF_8)));
    }

    private void createFile(String fileName, byte[] contents)
    {
        try (FileOutputStream out = MainActivity.this.openFileOutput(fileName, MODE_PRIVATE))
        {
            out.write(contents);
        }
        catch (IOException e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Не удалось создать файл")
                    .setPositiveButton("Ок", (dialog, which) -> { })
                    .show();
        }
    }

    private String readFile(String fileName)
    {
        try (FileInputStream in = MainActivity.this.openFileInput(fileName))
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

    private void deleteFileAlert(String fileName)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Вы точно хотите удалить файл " + fileName + "?")
                .setPositiveButton("Да", (dialog, which) -> deleteFile(fileName))
                .setNegativeButton("Отмена", (dialog, which) -> { })
                .show();
    }

    private void appendToFile(String fileName, byte[] contents)
    {
        File file = MainActivity.this.getFileStreamPath(fileName);
        if (!file.exists())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Файл не найден")
                    .setPositiveButton("Ок", (dialog, which) -> { })
                    .show();
            return;
        }
        try (FileOutputStream out = MainActivity.this.openFileOutput(fileName,
                MODE_PRIVATE | MODE_APPEND))
        {
            out.write(contents);
        }
        catch (IOException e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Не удалось изменить файл")
                    .setPositiveButton("Ок", (dialog, which) -> { })
                    .show();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        TextView textFileContents = findViewById(R.id.text_main_contents);
        outState.putString("ARGUMENT_TEXT_FILE_CONTENTS", textFileContents.getText().toString());
        EditText editFileName = findViewById(R.id.edit_main_name);
        outState.putString("ARGUMENT_EDIT_FILE_NAME", editFileName.getText().toString());
        EditText editFileContents = findViewById(R.id.edit_main_contents);
        outState.putString("ARGUMENT_EDIT_FILE_CONTENTS", editFileContents.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        TextView textFileContents = findViewById(R.id.text_main_contents);
        textFileContents.setText(savedInstanceState.getString("ARGUMENT_TEXT_FILE_CONTENTS"));
        EditText editFileName = findViewById(R.id.edit_main_name);
        editFileName.setText(savedInstanceState.getString("ARGUMENT_EDIT_FILE_NAME"));
        EditText editFileContents = findViewById(R.id.edit_main_contents);
        editFileContents.setText(savedInstanceState.getString("ARGUMENT_EDIT_FILE_CONTENTS"));
    }
}