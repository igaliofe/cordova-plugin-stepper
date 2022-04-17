package org.apache.cordova.stepper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import org.apache.cordova.BuildConfig;
import org.apache.cordova.stepper.util.API26Wrapper;
import android.content.SharedPreferences;
public class AppUpdatedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        SharedPreferences prefs = context.getSharedPreferences("pedometer", Context.MODE_PRIVATE);
        Boolean isStartService = prefs.getBoolean("isStartService", false);
        if(!isStartService){
          return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            API26Wrapper.startForegroundService(context, new Intent(context, SensorListener.class));
        } else {
            context.startService(new Intent(context, SensorListener.class));
        }
    }

}
