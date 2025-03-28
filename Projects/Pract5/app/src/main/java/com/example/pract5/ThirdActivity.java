package com.example.pract5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        RecyclerView recycler = findViewById(R.id.recycler_third);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<CustomItem> items = new ArrayList<>(List.of(
                new CustomItem("Яйцо", R.drawable.image_third_egg1),
                new CustomItem("Другое Яйцо", R.drawable.image_third_egg2),
                new CustomItem("Большое Яйцо", R.drawable.image_third_egg3),
                new CustomItem("Яйцо", R.drawable.image_third_egg1),
                new CustomItem("Другое Яйцо", R.drawable.image_third_egg2),
                new CustomItem("Большое Яйцо", R.drawable.image_third_egg3),
                new CustomItem("Яйцо", R.drawable.image_third_egg1),
                new CustomItem("Другое Яйцо", R.drawable.image_third_egg2),
                new CustomItem("Большое Яйцо", R.drawable.image_third_egg3)
        ));

        CustomAdapter adapter = new CustomAdapter(items);
        recycler.setAdapter(adapter);
    }
}