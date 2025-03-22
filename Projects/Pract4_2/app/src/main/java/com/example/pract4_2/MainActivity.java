package com.example.pract4_2;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

        Button buttonFragment1 = findViewById(R.id.button_main_fragment1);
        Button buttonFragment2 = findViewById(R.id.button_main_fragment2);
        Button buttonFragment3 = findViewById(R.id.button_main_fragment3);

        buttonFragment1.setOnClickListener(v -> replaceFragment(new Fragment1()));
        buttonFragment2.setOnClickListener(v -> replaceFragment(new Fragment2()));
        buttonFragment3.setOnClickListener(v -> replaceFragment(new Fragment3()));

        if (savedInstanceState == null)
        {
            replaceFragment(new Fragment1());
        }
    }

    private void replaceFragment(Fragment newFragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer_main, newFragment)
                .commit();
    }
}