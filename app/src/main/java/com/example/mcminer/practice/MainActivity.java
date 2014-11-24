package com.example.mcminer.practice;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;

public class MainActivity extends Activity implements BootstrapNotifier {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ((mcminerApp) getApplication() ).setmainactivity(this);


    }
	
	public void cancelNotification(int notificationId){
		
		if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
            nMgr.cancel(notificationId);
        }
	}

    @Override
    public void didEnterRegion(Region region) {

            // define sound URI, the sound to be played when there's a notification
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            // intent triggered, you can add other intent for other actions
        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
        PendingIntent pIntent2 = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);


        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dropbox.com/s/rc0slgrmxjh9ofm/mcminer.png?dl=0"));
        notificationIntent.setClassName("com.android.browser",
                "com.android.browser.BrowserActivity");
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);

            // this is it, we'll build the notification!
            // in the addAction method, if you don't want any icon, just set the first param to 0
            Notification mNotification = new Notification.Builder(this)

                    .setContentTitle("Play McMiner!")
                    .setContentText("Like to play our McMiner game?")
                    .setPriority(Notification.PRIORITY_MAX)
                    .setWhen(0)
                    .setSmallIcon(R.drawable.mcminer)
                    .setContentIntent(pIntent)
                    .setSound(soundUri)

                    .addAction(R.drawable.check, "Yes", pIntent)
                    .addAction(R.drawable.icon, "No", pIntent2)

                    .build();



            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // If you want to hide the notification after it was selected, do the code below
            // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, mNotification);


    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}
