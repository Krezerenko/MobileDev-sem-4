package com.example.pract10_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        EditText editId = findViewById(R.id.edit_main_id);
        EditText editName = findViewById(R.id.edit_main_name);
        EditText editWeight = findViewById(R.id.edit_main_weight);
        EditText editPrice = findViewById(R.id.edit_main_price);
        EditText editAmount = findViewById(R.id.edit_main_amount);

        TextView textId = findViewById(R.id.text_main_id);
        TextView textName = findViewById(R.id.text_main_name);
        TextView textWeight = findViewById(R.id.text_main_weight);
        TextView textPrice = findViewById(R.id.text_main_price);
        TextView textAmount = findViewById(R.id.text_main_amount);

        DBHelper helper = new DBHelper(this);

        Button buttonAdd = findViewById(R.id.button_main_add);
        buttonAdd.setOnClickListener(v ->
        {
            DBEntry entry = new DBEntry(
                    0,
                    editName.getText().toString(),
                    editWeight.getText().toString(),
                    editPrice.getText().toString(),
                    Integer.parseInt(editAmount.getText().toString()));
            boolean result = helper.addEntry(entry);
            if (!result)
            {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonGet = findViewById(R.id.button_main_get);
        buttonGet.setOnClickListener(v ->
        {
            DBEntry entry = helper.getEntry(editId.getText().toString());
            if (entry == null)
            {
                Toast.makeText(this, "Запись не найдена", Toast.LENGTH_SHORT).show();
                return;
            }
            textId.setText(String.valueOf(entry.getId()));
            textName.setText(entry.getName());
            textWeight.setText(entry.getWeight());
            textPrice.setText(entry.getPrice());
            textAmount.setText(String.valueOf(entry.getAmount()));
        });

        Button buttonDelete = findViewById(R.id.button_main_delete);
        buttonDelete.setOnClickListener(v ->
        {
            boolean result = helper.deleteEntry(editId.getText().toString());
            if (!result)
            {
                Toast.makeText(this, "Запись не найдена", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonSet = findViewById(R.id.button_main_set);
        buttonSet.setOnClickListener(v ->
        {
            DBEntry entry = new DBEntry(
                    Integer.parseInt(editId.getText().toString()),
                    editName.getText().toString(),
                    editWeight.getText().toString(),
                    editPrice.getText().toString(),
                    Integer.parseInt(editAmount.getText().toString()));
            boolean result = helper.setEntry(entry);
            if (!result)
            {
                Toast.makeText(this, "Запись не найдена", Toast.LENGTH_SHORT).show();
            }
        });
    }
}