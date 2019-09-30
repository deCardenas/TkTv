package com.aprendiendo.tktv;

import android.app.Application;

import com.aprendiendo.tktv.db.DatabaseManager;
import com.aprendiendo.tktv.db.DbHelper;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper helper = new DbHelper(getApplicationContext());
        DatabaseManager.initializeInstance(helper);
    }
}
