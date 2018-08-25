package com.sadiya.pedometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sadiya.pedometer.util.Logger;
import com.sadiya.pedometer.util.Util;

import com.sadiya.pedometer.BuildConfig;

public class ShutdownRecevier extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("shutting down");

        context.startService(new Intent(context, SensorListener.class));
        context.getSharedPreferences("pedometer", Context.MODE_PRIVATE).edit()
                .putBoolean("correctShutdown", true).commit();

        Database db = Database.getInstance(context);
        if (db.getSteps(Util.getToday()) == Integer.MIN_VALUE) {
            int steps = db.getCurrentSteps();
            int pauseDifference = steps -
                    context.getSharedPreferences("pedometer", Context.MODE_PRIVATE)
                            .getInt("pauseCount", steps);
            db.insertNewDay(Util.getToday(), steps - pauseDifference);
            if (pauseDifference > 0) {
                context.getSharedPreferences("pedometer", Context.MODE_PRIVATE).edit()
                        .putInt("pauseCount", steps).commit();
            }
        } else {
            db.addToLastEntry(db.getCurrentSteps());
        }
        db.close();
    }

}
