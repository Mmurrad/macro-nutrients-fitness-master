package com.water.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.water.Database.DrinkDataSource;
import com.water.MainWindow.AlarmHelper;
import com.water.Settings.PrefsHelper;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context pContext, Intent intent) {
        DrinkDataSource db= new DrinkDataSource(pContext);
        db.open();
       int waterNeed= PrefsHelper.getWaterNeedPrefs(pContext);
       db.createMissingDateLog(0,waterNeed);
       AlarmHelper.setDBAlarm(pContext);
    }
}


