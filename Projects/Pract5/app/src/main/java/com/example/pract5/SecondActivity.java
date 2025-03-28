package com.example.pract5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

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

        int listNumber = args.getInt("ARGUMENT_LIST_NUMBER");
        String[] varieties;
        switch (listNumber)
        {
            case 0 :
                varieties = getResources().getStringArray(R.array.second_apples);
                break;
            case 1 :
                varieties = getResources().getStringArray(R.array.second_oranges);
                break;
            case 2 :
                varieties = getResources().getStringArray(R.array.second_tangerines);
                break;
            default:
                return;
        }
        ArrayList<String> varietiesList = new ArrayList<>(Arrays.asList(varieties));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, varietiesList);
        ListView varietiesView = findViewById(R.id.list_second_varieties);
        varietiesView.setAdapter(adapter);

        EditText editNewName = findViewById(R.id.edit_second_newName);
        Button buttonAddNewName = findViewById(R.id.button_second_addNewName);
        buttonAddNewName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newName = editNewName.getText().toString();
                if (newName.isEmpty()) return;

                adapter.add(newName);
                adapter.notifyDataSetChanged();
            }
        });

        varietiesView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                varietiesList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}