package com.water.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.water.MainWindow.AlarmHelper;


/**
 * Created by Abeer on 8/9/2017.
 */

public class StopNotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmHelper.stopNotificationsAlarm(context);
        System.out.println("fired!");
    }
}
