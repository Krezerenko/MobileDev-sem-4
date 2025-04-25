package com.example.pract7;

import android.app.DatePickerDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class DialogService extends Service
{
    private final IBinder binder = new DialogBinder();

    public class DialogBinder extends Binder
    {
        DialogService getService()
        {
            return DialogService.this;
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public void showAlert(Context parentContext,
                          DialogInterface.OnClickListener onPositive,
                          DialogInterface.OnClickListener onNegative)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(parentContext);
        alertBuilder.setTitle("yes?")
                .setPositiveButton("ok", onPositive)
                .setNegativeButton("no", onNegative)
                .show();
    }

    public void showTimePicker(Context parentContext, TimePickerDialog.OnTimeSetListener onPick)
    {
        new TimePickerDialog(parentContext, onPick, 12, 0, true).show();
    }

    public void showDatePicker(Context parentContext, DatePickerDialog.OnDateSetListener onPick)
    {
        new DatePickerDialog(parentContext, onPick, 2025, 3, 25).show();
    }

    public void showCustom(FragmentManager fragmentManager,
                           DialogInterface.OnClickListener onPositive,
                           DialogInterface.OnClickListener onNegative)
    {
        new CustomDialog(onPositive, onNegative).show(fragmentManager, "custom");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }
}