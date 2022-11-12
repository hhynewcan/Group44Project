package com.example.group44project;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver
{
    /**
     * Set up the notification
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Group44")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Grocery Shoping Reminder")
                .setContentText("Hey! It is time to do your grocery shopping!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(44, builder.build());
    }
}
