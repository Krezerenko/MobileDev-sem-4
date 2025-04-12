package com.example.pract6;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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

        ActionBar bar = getSupportActionBar();
        if (bar == null) return;

        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Вторая страница");

        BottomNavigationView bottomNav = findViewById(R.id.navigation_second);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                int id = item.getItemId();
                if (id == R.id.item_bottom_menu_bro1)
                {
                    bar.setTitle("Услышал");
                    changeFragment(new Fragment4(), fragmentManager);
                }
                else if (id == R.id.item_bottom_menu_bro2)
                {
                    bar.setTitle("Не слышу");
                    changeFragment(new Fragment5(), fragmentManager);
                }
                else if (id == R.id.item_bottom_menu_grub)
                {
                    bar.setTitle("Гусеница");
                    changeFragment(new Fragment6(), fragmentManager);
                }

                return true;
            }
        });
    }

    private void changeFragment(Fragment newFragment, FragmentManager manager)
    {
        manager.beginTransaction()
                .replace(R.id.fragmentContainer_second, newFragment)
                .commit();
    }
}