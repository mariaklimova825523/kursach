package com.example.submon;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.submon.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    static final String CHANNEL_ID = "Chanel 1";


    //
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);

        createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Poggers",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Keklul");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

    }

    public void makeNotif(View view) {
        Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            OffsetDateTime offset = OffsetDateTime.now();
            Log.d("MainActivity", "Notification Made, today is " + offset.getDayOfMonth());
        }



    }


    public void Change(View view) {
        Log.d("MainActivity","Change");
        Fragment fragment;
        fragment = null;


        switch (view.getId()) {
            case R.id.button:
                fragment = new fragment_all();
                break;
            case R.id.button2:
                fragment = new fragment_add();
                break;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        if (fragment != null) {
            ft.replace(R.id.fragmentPlace, fragment);
        }
        ft.commit();
    }


}
