package com.example.pract7;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomDialog extends DialogFragment
{
    private final DialogInterface.OnClickListener onPositive;
    private final DialogInterface.OnClickListener onNegative;
    public CustomDialog(DialogInterface.OnClickListener onPositive, DialogInterface.OnClickListener onNegative)
    {
        this.onPositive = onPositive;
        this.onNegative = onNegative;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_custom, null))
                .setPositiveButton("yes", onPositive)
                .setNegativeButton("no", onNegative);
        return builder.create();
    }
}
