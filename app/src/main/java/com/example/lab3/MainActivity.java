package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "channel_id01";
    public static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send =findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showNotification();
            }

        });

}

    private void showNotification(){

        createNotificationChannel();

        Intent mainIntent = new Intent(this, next.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("Title of Notification");
        builder.setContentText("This is the description");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());


    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }



    }


}





