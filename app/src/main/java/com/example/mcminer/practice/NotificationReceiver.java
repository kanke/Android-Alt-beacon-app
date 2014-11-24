package com.example.mcminer.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationReceiver extends Activity {



    @Override
	protected void onCreate(Bundle savedInstanceState) {

     Intent myIntent;

        myIntent = new Intent();

        myIntent.setAction(Intent.ACTION_VIEW);
        myIntent.setData(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

	}


}