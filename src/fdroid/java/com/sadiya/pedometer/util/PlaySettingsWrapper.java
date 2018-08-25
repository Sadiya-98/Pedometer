
package com.sadiya.pedometer.util;

import android.os.Bundle;
import android.preference.Preference;

import com.sadiya.pedometer.ui.Activity_Main;


public class PlaySettingsWrapper {

    public static void setupAccountSetting(final Preference account,
                                           final Bundle savedInstanceState,
                                           final Activity_Main main) {
        account.setSummary("This feature is not available on the F-Droid version of the app");
        account.setEnabled(false);
    }

    public static void onSavedInstance(final Bundle outState, final Activity_Main main) {

    }

}
