package com.example.pract11;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, FourthActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_egg)
                .setContentTitle("Уведомление с задержкой")
                .setContentText("Немножечко подождали");
        context.getSystemService(NotificationManager.class).notify(2, builder.build());
    }
}
