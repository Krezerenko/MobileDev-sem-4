package com.example.pract7;

import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private DialogService dialogService;
    private boolean isBound;
    private final ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            DialogService.DialogBinder binder = (DialogService.DialogBinder) service;
            dialogService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            isBound = false;
        }
    };

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

        Intent serviceIntent = new Intent(MainActivity.this, DialogService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);

        TextView textAlert = findViewById(R.id.text_main_alert);
        Button buttonAlert = findViewById(R.id.button_main_alert);
        buttonAlert.setOnClickListener((v ->
        {
            if (!isBound) return;
            dialogService.showAlert(
                    MainActivity.this,
                    (dialog, which) -> textAlert.setText("ok"),
                    (dialog, which) -> textAlert.setText("no"));
        }));
        TextView textTime = findViewById(R.id.text_main_time);
        Button buttonTime = findViewById(R.id.button_main_time);
        buttonTime.setOnClickListener((v ->
        {
            if (!isBound) return;
            dialogService.showTimePicker(MainActivity.this,
                    (view, hourOfDay, minute) ->
                    {
                        textTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                    });
        }));
        TextView textDate = findViewById(R.id.text_main_date);
        Button buttonDate = findViewById(R.id.button_main_date);
        buttonDate.setOnClickListener((v ->
        {
            if (!isBound) return;
            dialogService.showDatePicker(MainActivity.this,
                    (view, year, month, dayOfMonth) ->
                    {
                        textDate.setText(String.format("%d.%02d.%02d", year, month + 1, dayOfMonth));
                    });
        }));
        TextView textCustom = findViewById(R.id.text_main_custom);
        Button buttonCustom = findViewById(R.id.button_main_custom);
        buttonCustom.setOnClickListener((v ->
        {
            if (!isBound) return;
            dialogService.showCustom(
                    getSupportFragmentManager(),
                    (dialog, which) -> textCustom.setText("yes"),
                    (dialog, which) -> textCustom.setText("no"));
        }));
    }
}