package com.example.mcminer.practice;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

/**
 * Created by kankeishaku on 22/11/2014.
 */
public class mcminerApp extends Application implements BootstrapNotifier {

    private BeaconManager mBeaconManager;
    private Region mAllBeaconsRegion;

    private MainActivity mainActivity;
    private BackgroundPowerSaver mBackgroundPowerSaver;
    @SuppressWarnings("unused")
    private RegionBootstrap mRegionBootstrap;

    @Override
    public void didEnterRegion(Region region) {
        //1
        // This call to disable will make it so the activity below only gets launched the first time a beacon is seen (until the next time the app is launched)
        // if you want the Activity to launch every single time beacons come into view, remove this call.
         /*   mRegionBootstrap.disable();
        Intent intent = new Intent(this, MainActivity.class);
        // IMPORTANT: in the AndroidManifest.xml definition of this activity, you must set android:launchMode="singleInstance" or you will get two instances
        // created when a user launches the activity manually and it gets launched from here.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);*/
         if (mainActivity != null){

            mainActivity.didEnterRegion(region );
         } else {
             Log.i("no activity found","stop");
         }


        }

    public void setmainactivity(MainActivity mainactivity) {

        this.mainActivity = mainactivity;

    }


    public void cancelNotification(int notificationId){

        if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
            nMgr.cancel(notificationId);
        }
    }


    @Override
    public void didExitRegion(Region region) {
    //0
    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }

    public void onCreate(){

        mAllBeaconsRegion = new Region("McMiner",Identifier.parse("2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6"),Identifier.fromInt(1), Identifier.fromInt(1));

        mBeaconManager = BeaconManager.getInstanceForApplication(this);
        mBackgroundPowerSaver = new BackgroundPowerSaver(this);
        mRegionBootstrap = new RegionBootstrap(this, mAllBeaconsRegion);

        mBeaconManager.setBackgroundScanPeriod(3000);
        mBeaconManager.setBackgroundBetweenScanPeriod(3000);



    }
}

