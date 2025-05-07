package com.example.pract11;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FourthActivity extends AppCompatActivity
{
    public static final String CHANNEL_ID = "egg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNotificationChannel();
        Button buttonNotifyInstant = findViewById(R.id.button_fourth_notifyInstant);
        buttonNotifyInstant.setOnClickListener(v ->
        {
            NotificationCompat.Builder builder = new
                    NotificationCompat.Builder(v.getContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_egg)
                    .setContentTitle("Моментальное уведомление")
                    .setContentText("Это было быстро...");
            getSystemService(NotificationManager.class).notify(1, builder.build());
        });
        Button buttonNotifyDelayed = findViewById(R.id.button_fourth_notifyDelayed);
        buttonNotifyDelayed.setOnClickListener(v ->
        {
            Intent intent = new Intent(v.getContext(), AlarmReceiver.class);
            PendingIntent delayedIntent = PendingIntent.getBroadcast(v.getContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            try
            {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + 1000, delayedIntent);
            }
            catch (SecurityException e)
            {
                Toast.makeText(this, "Не удалось показать уведомление",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Яйцо", NotificationManager.IMPORTANCE_HIGH);

            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }
}

