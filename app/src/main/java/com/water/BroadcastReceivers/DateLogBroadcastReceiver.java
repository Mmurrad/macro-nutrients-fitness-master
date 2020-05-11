package com.water.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.water.Database.DrinkDataSource;
import com.water.MainWindow.AlarmHelper;
import com.water.MainWindow.DateHandler;
import com.water.Settings.PreferenceKey;
import com.water.Settings.PrefsHelper;

import java.util.Date;

/**
 * Created by Abeer on 3/29/2017.
 */

public class DateLogBroadcastReceiver extends BroadcastReceiver {

    private DrinkDataSource db;
    private Context mContext;


    @Override
    public void onReceive(Context context, Intent intent) {
        mContext =context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean NotificationSet= prefs.getBoolean(PreferenceKey.PREF_IS_ENABLED,true);
                insertDateLog();

                  if(NotificationSet)
                  { AlarmHelper.setNotificationsAlarm(context);
                    AlarmHelper.setCancelNotificationAlarm(context);}

    }



    private void insertDateLog() {

        int waterNeed= PrefsHelper.getWaterNeedPrefs(mContext);
        db= new DrinkDataSource(mContext);
        db.open();
         db.createDateLog(0,waterNeed, DateHandler.getCurrentDate());
  //     db.createMissingDateLog(0,waterNeed);

        System.out.println("db alarm fired "+ DateHandler.getCurrentDate());
        System.out.println("new record inserted: "+new Date()+" water need: "+waterNeed+" Water drank: "+0);

        Intent local = new Intent();
        local.setAction("com.update.view.action");
        mContext.sendBroadcast(local);

    }

}
