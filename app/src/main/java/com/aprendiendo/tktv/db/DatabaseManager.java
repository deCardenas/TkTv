package com.aprendiendo.tktv.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public class DatabaseManager {
    private int openCounter = 0;
    private static DatabaseManager instance;
    private static SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            openHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null)
            throw new IllegalArgumentException(DatabaseManager.class.getName() +
                    " is not initialized, call initializeInstance() method first");
        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        openCounter++;
        if (openCounter == 1)
            database = openHelper.getWritableDatabase();
        return database;
    }

    public synchronized SQLiteDatabase openReadableDatabase() {
        openCounter++;
        if (openCounter == 1)
            database = openHelper.getReadableDatabase();
        return database;
    }

    public synchronized void closeDatabase() {
        openCounter--;
        if (openCounter == 0)
            database.close();
    }
}
