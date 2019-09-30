package com.aprendiendo.tktv.db.contract;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.db.DatabaseManager;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class ListContract {
    public static final int MOVIE = 0;
    public static final int TV_SHOW = 1;
    public static final int PERSON = 2;

    public static final int TYPE = 0;
    public static final int TOTAL_PAGES = 1;
    public static final int TOTAL_RESULTS = 2;

    public static class ListEntry {
        public static final String TABLE = "list";

        public static final String TYPE = "type";
        public static final String TOTAL_PAGES = "total_pages";
        public static final String TOTAL_RESULTS = "total_results";
    }

    public static final String CREATE = "CREATE TABLE " + ListEntry.TABLE + "(" +
            ListEntry.TYPE + " INTEGER PRIMARY KEY," +
            ListEntry.TOTAL_PAGES + " INTEGER," +
            ListEntry.TOTAL_RESULTS + " INTEGER" +
            ")";

    public static final String DROP = "DROP TABLE IF EXISTS " +ListEntry.TABLE ;

    public static long insert(List list) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(ListEntry.TYPE, list.getType());
        values.put(ListEntry.TOTAL_PAGES, list.getTotal_pages());
        values.put(ListEntry.TOTAL_RESULTS, list.getTotal_results());
        long id = db.insert(ListEntry.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public static long update(List list) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(ListEntry.TOTAL_PAGES, list.getTotal_pages());
        values.put(ListEntry.TOTAL_RESULTS, list.getTotal_results());
        long id = db.update(ListEntry.TABLE, values, ListEntry.TYPE + "=" + list.getType(), null);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public static void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(ListEntry.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public static List getListByType(int type){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ListEntry.TABLE + " WHERE " + ListEntry.TYPE + "=" + type, null);
        if (c == null)
            return null;
        if (!c.moveToFirst())
            return null;
        else {
            List list = new List();
            list.setType(type);
            list.setTotal_pages(c.getInt(TOTAL_PAGES));
            list.setTotal_results(c.getInt(TOTAL_RESULTS));
            c.close();
            DatabaseManager.getInstance().closeDatabase();
            return list;
        }
    }
}
