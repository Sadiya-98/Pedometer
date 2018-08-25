

package com.sadiya.pedometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class PowerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        SharedPreferences prefs =
                context.getSharedPreferences("pedometer", Context.MODE_MULTI_PROCESS);
        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction()) &&
                !prefs.contains("pauseCount")) {
            context.startService(new Intent(context, SensorListener.class)
                    .putExtra("action", SensorListener.ACTION_PAUSE));
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction()) &&
                prefs.contains("pauseCount")) {
            context.startService(new Intent(context, SensorListener.class)
                    .putExtra("action", SensorListener.ACTION_PAUSE));
        }
    }
}
