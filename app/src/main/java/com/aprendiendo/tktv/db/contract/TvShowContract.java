package com.aprendiendo.tktv.db.contract;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.db.DatabaseManager;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowContract {
    public static final int LIST = 0;
    public static final int ITEM = 1;

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int ORIGINAL_NAME = 2;
    public static final int OVERVIEW = 3;
    public static final int POSTER_PATH = 4;
    public static final int BACKDROP_PATH = 5;
    public static final int FIRST_AIR_DATE = 6;
    public static final int ORIGINAL_LANGUAGE = 7;
    public static final int EPISODE_RUN_TIME = 8;
    public static final int HOMEPAGE = 9;
    public static final int IN_PRODUCTION = 10;
    public static final int LAST_AIR_DATE = 11;
    public static final int NUMBER_OF_EPISODES = 12;
    public static final int NUMBER_OF_SEASONS = 13;
    public static final int STATUS = 14;
    public static final int TYPE = 15;
    public static final int POSTER = 16;
    public static final int BACKDROP = 17;

    static class TvShowEntry {
        public static final String TABLE = "tv_show";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ORIGINAL_NAME = "original_name";
        public static final String OVERVIEW = "overview";
        public static final String POSTER_PATH = "poster_path";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String FIRST_AIR_DATE = "first_air_date";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String EPISODE_RUN_TIME = "episode_run_time";
        public static final String HOMEPAGE = "homepage";
        public static final String IN_PRODUCTION = "in_production";
        public static final String LAST_AIR_DATE = "last_air_date";
        public static final String NUMBER_OF_EPISODES = "number_of_episodes";
        public static final String NUMBER_OF_SEASONS = "number_of_seasons";
        public static final String STATUS = "status";
        public static final String TYPE = "type";
        public static final String DOWNLOADED = "downloaded";
        public static final String POSTER = "poster";
        public static final String BACKDROP = "backdrop";
    }

    public static final String CREATE = "CREATE TABLE " +
            TvShowEntry.TABLE + "(" +
            TvShowEntry.ID + " INTEGER PRIMARY KEY," +
            TvShowEntry.NAME + " TEXT," +
            TvShowEntry.ORIGINAL_NAME + " TEXT," +
            TvShowEntry.OVERVIEW + " TEXT," +
            TvShowEntry.POSTER_PATH + " TEXT," +
            TvShowEntry.BACKDROP_PATH + " TEXT," +
            TvShowEntry.FIRST_AIR_DATE + " TEXT," +
            TvShowEntry.ORIGINAL_LANGUAGE + " TEXT," +
            TvShowEntry.EPISODE_RUN_TIME + " INTEGER," +
            TvShowEntry.HOMEPAGE + " TEXT," +
            TvShowEntry.IN_PRODUCTION + " BOOLEAN," +
            TvShowEntry.LAST_AIR_DATE + " TEXT," +
            TvShowEntry.NUMBER_OF_EPISODES + " INTEGER," +
            TvShowEntry.NUMBER_OF_SEASONS + " INTEGER," +
            TvShowEntry.STATUS + " TEXT," +
            TvShowEntry.TYPE + " TEXT," +
            TvShowEntry.DOWNLOADED + " INTEGER," +
            TvShowEntry.POSTER + " BLOB," +
            TvShowEntry.BACKDROP + " BLOB" +
            ")";

    public static final String DROP = "DROP TABLE IF EXISTS " + TvShowEntry.TABLE;

    public static long insert(TvShow tvShow) {
        long id;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(TvShowEntry.ID, tvShow.getId());
        values.put(TvShowEntry.NAME, tvShow.getName());
        values.put(TvShowEntry.ORIGINAL_NAME, tvShow.getOriginal_name());
        values.put(TvShowEntry.OVERVIEW, tvShow.getOverview());
        values.put(TvShowEntry.POSTER_PATH, tvShow.getPoster_path());
        values.put(TvShowEntry.BACKDROP_PATH, tvShow.getBackdrop_path());
        values.put(TvShowEntry.FIRST_AIR_DATE, tvShow.getFirst_air_date());
        values.put(TvShowEntry.ORIGINAL_LANGUAGE, tvShow.getOriginal_language());
        values.put(TvShowEntry.DOWNLOADED, LIST);
        values.put(TvShowEntry.POSTER_PATH,tvShow.getPoster_byte());
        values.put(TvShowEntry.BACKDROP, tvShow.getBackdrop_byte());

        id = db.insert(TvShowEntry.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public static long update(TvShow tvShow) {
        long id;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(TvShowEntry.ID, tvShow.getId());
        values.put(TvShowEntry.NAME, tvShow.getName());
        values.put(TvShowEntry.ORIGINAL_NAME, tvShow.getOriginal_name());
        values.put(TvShowEntry.OVERVIEW, tvShow.getOverview());
        values.put(TvShowEntry.POSTER_PATH, tvShow.getPoster_path());
        values.put(TvShowEntry.BACKDROP_PATH, tvShow.getBackdrop_path());
        values.put(TvShowEntry.FIRST_AIR_DATE, tvShow.getFirst_air_date());
        values.put(TvShowEntry.ORIGINAL_LANGUAGE, tvShow.getOriginal_language());
        values.put(TvShowEntry.EPISODE_RUN_TIME, tvShow.getEpisode_run_time()[0]);
        values.put(TvShowEntry.HOMEPAGE, tvShow.getHomepage());
        values.put(TvShowEntry.IN_PRODUCTION, tvShow.isIn_production());
        values.put(TvShowEntry.LAST_AIR_DATE, tvShow.getLast_air_date());
        values.put(TvShowEntry.NUMBER_OF_EPISODES, tvShow.getNumber_of_episodes());
        values.put(TvShowEntry.NUMBER_OF_SEASONS, tvShow.getNumber_of_seasons());
        values.put(TvShowEntry.STATUS, tvShow.getStatus());
        values.put(TvShowEntry.TYPE, tvShow.getType());
        values.put(TvShowEntry.DOWNLOADED, ITEM);
        values.put(TvShowEntry.POSTER_PATH,tvShow.getPoster_byte());
        values.put(TvShowEntry.BACKDROP, tvShow.getBackdrop_byte());

        id = db.update(TvShowEntry.TABLE, values, TvShowEntry.ID + "=" + tvShow.getId(), null);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public static void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TvShowEntry.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public static ArrayList<TvShow> getAllTvShows() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ArrayList<TvShow> tvShows = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TvShowEntry.TABLE, null);
        if (c.moveToFirst())
            do {
                TvShow tvShow = new TvShow();
                tvShow.setId(c.getInt(ID));
                tvShow.setName(c.getString(NAME));
                tvShow.setOriginal_name(c.getString(ORIGINAL_NAME));
                tvShow.setOverview(c.getString(OVERVIEW));
                tvShow.setPoster_path(c.getString(POSTER_PATH));
                tvShow.setBackdrop_path(c.getString(BACKDROP_PATH));
                tvShow.setFirst_air_date(c.getString(FIRST_AIR_DATE));
                tvShow.setOriginal_language(c.getString(ORIGINAL_LANGUAGE));
                tvShow.setPoster_byte(c.getBlob(POSTER));
                tvShow.setBackdrop_byte(c.getBlob(BACKDROP));
                tvShows.add(tvShow);
            } while (c.moveToNext());
        c.close();
        DatabaseManager.getInstance().closeDatabase();
        return tvShows;
    }

    public static TvShow getTvShowById(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TvShowEntry.TABLE +
                " WHERE " + TvShowEntry.ID + "=" + id + " AND " + TvShowEntry.DOWNLOADED + "=" + ITEM, null);
        if (!c.moveToFirst())
            return null;
        TvShow tvShow = new TvShow();
        tvShow.setId(c.getInt(ID));
        tvShow.setName(c.getString(NAME));
        tvShow.setOriginal_name(c.getString(ORIGINAL_NAME));
        tvShow.setOverview(c.getString(OVERVIEW));
        tvShow.setPoster_path(c.getString(POSTER_PATH));
        tvShow.setBackdrop_path(c.getString(BACKDROP_PATH));
        tvShow.setFirst_air_date(c.getString(FIRST_AIR_DATE));
        tvShow.setOriginal_language(c.getString(ORIGINAL_LANGUAGE));
        tvShow.setEpisode_run_time(new int[]{c.getInt(EPISODE_RUN_TIME)});
        tvShow.setHomepage(c.getString(HOMEPAGE));
        tvShow.setIn_production(c.getInt(IN_PRODUCTION)>0);
        tvShow.setLast_air_date(c.getString(LAST_AIR_DATE));
        tvShow.setNumber_of_episodes(c.getInt(NUMBER_OF_EPISODES));
        tvShow.setNumber_of_seasons(c.getInt(NUMBER_OF_SEASONS));
        tvShow.setStatus(c.getString(STATUS));
        tvShow.setType(c.getString(TYPE));
        tvShow.setPoster_byte(c.getBlob(POSTER));
        tvShow.setBackdrop_byte(c.getBlob(BACKDROP));
        c.close();
        DatabaseManager.getInstance().closeDatabase();
        return tvShow;
    }
}
