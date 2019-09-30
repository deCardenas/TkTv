package com.aprendiendo.tktv.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aprendiendo.tktv.db.contract.ListContract;
import com.aprendiendo.tktv.db.contract.MovieContract;
import com.aprendiendo.tktv.db.contract.TvShowContract;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Tekton.db";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ListContract.CREATE);
        db.execSQL(MovieContract.CREATE);
        db.execSQL(TvShowContract.CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(ListContract.DROP);
            db.execSQL(MovieContract.DROP);
            db.execSQL(TvShowContract.DROP);
            onCreate(db);
        }
    }
}
