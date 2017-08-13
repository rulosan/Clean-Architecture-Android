package com.example.jhordan.security;

import android.content.ContentResolver;
import android.provider.Settings;

/**
 * Created by rulosan on 8/13/17.
 */

public class SecurityDetector {

    public boolean detectDeveloperTools(ContentResolver resolver){
        int result = Settings.Secure.getInt(resolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        return result == 1;
    }
}
