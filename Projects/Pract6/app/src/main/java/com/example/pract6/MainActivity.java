package com.example.pract6;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

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

        drawer = findViewById(R.id.main);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ActionBar bar = getSupportActionBar();
        if (bar == null) return;

        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Яйца");

        NavigationView nav = drawer.findViewById(R.id.navigation_main);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                int id = item.getItemId();
                if (id == R.id.item_side_menu_first)
                    changeFragment(new Fragment1(), fragmentManager);
                else if (id == R.id.item_side_menu_second)
                    changeFragment(new Fragment2(), fragmentManager);
                else if (id == R.id.item_side_menu_third)
                    changeFragment(new Fragment3(), fragmentManager);
                else if (id == R.id.item_side_menu_transition)
                {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }

                drawer.closeDrawers();
                return true;
            }
        });
    }

    private void changeFragment(Fragment newFragment, FragmentManager manager)
    {
        manager.beginTransaction()
                .replace(R.id.fragmentContainer_main, newFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (toggle.onOptionsItemSelected(item)) return true;

        return super.onOptionsItemSelected(item);
    }
}