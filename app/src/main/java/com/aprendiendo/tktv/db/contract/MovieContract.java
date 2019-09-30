package com.aprendiendo.tktv.db.contract;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.db.DatabaseManager;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MovieContract {
    static final int LIST = 0;
    static final int ITEM = 1;
    public static final int ID = 0;
    public static final int TITLE = 1;
    public static final int ORIGINAL_TITLE = 2;
    public static final int OVERVIEW = 3;
    public static final int HOMEPAGE = 4;
    public static final int BUDGET = 5;
    public static final int BACKDROP_PATH = 6;
    public static final int POSTER_PATH = 7;
    public static final int RUNTIME = 8;
    public static final int STATUS = 9;
    public static final int ADULT = 10;
    public static final int RELEASE_DATE = 11;
    public static final int ORIGINAL_LANGUAGE = 12;
    public static final int REVENUE = 13;
    public static final int DOWNLOADED = 14;

    public static abstract class MovieEntry {
        public static final String TABLE = "movie";

        public static final String BUDGET = "budget";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String STATUS = "status";
        public static final String RUNTIME = "runtime";
        public static final String ADULT = "adult";
        public static final String HOMEPAGE = "homepage";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String POSTER_PATH = "poster_path";
        public static final String REVENUE = "revenue";
        public static final String DOWNLOADED = "downloaded";
    }

    public static final String CREATE = "CREATE TABLE " +
            MovieEntry.TABLE + "(" +
            MovieEntry.ID + " INTEGER PRIMARY KEY," +
            MovieEntry.TITLE + " TEXT," +
            MovieEntry.ORIGINAL_TITLE + " TEXT," +
            MovieEntry.OVERVIEW + " TEXT," +
            MovieEntry.HOMEPAGE + " TEXT," +
            MovieEntry.BUDGET + " INTEGER," +
            MovieEntry.BACKDROP_PATH + " TEXT," +
            MovieEntry.POSTER_PATH + " TEXT," +
            MovieEntry.RUNTIME + " INTEGER," +
            MovieEntry.STATUS + " TEXT," +
            MovieEntry.ADULT + " BOOLEAN," +
            MovieEntry.RELEASE_DATE + " TEXT," +
            MovieEntry.ORIGINAL_LANGUAGE + " TEXT," +
            MovieEntry.REVENUE + " INTEGER," +
            MovieEntry.DOWNLOADED + " INTEGER " +
            ")";

    public static final String DROP = "DROP TABLE IF EXISTS " + MovieEntry.TABLE;

    public static long insert(Movie movie) {
        long id;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieEntry.ID, movie.getId());
        values.put(MovieEntry.TITLE, movie.getTitle());
        values.put(MovieEntry.ORIGINAL_TITLE, movie.getOriginal_title());
        values.put(MovieEntry.OVERVIEW, movie.getOverview());
        values.put(MovieEntry.BACKDROP_PATH, movie.getBackdrop_path());
        values.put(MovieEntry.POSTER_PATH, movie.getPoster_path());
        values.put(MovieEntry.ADULT, movie.isAdult());
        values.put(MovieEntry.RELEASE_DATE, movie.getRelease_date());
        values.put(MovieEntry.ORIGINAL_LANGUAGE, movie.getOriginal_language());
        values.put(MovieEntry.DOWNLOADED, LIST);

        id = db.insert(MovieEntry.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public static long update(Movie movie) {
        long id;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MovieEntry.ID, movie.getId());
        values.put(MovieEntry.TITLE, movie.getTitle());
        values.put(MovieEntry.ORIGINAL_TITLE, movie.getOriginal_title());
        values.put(MovieEntry.OVERVIEW, movie.getOverview());
        values.put(MovieEntry.HOMEPAGE, movie.getHomepage());
        values.put(MovieEntry.BUDGET, movie.getBudget());
        values.put(MovieEntry.BACKDROP_PATH, movie.getBackdrop_path());
        values.put(MovieEntry.POSTER_PATH, movie.getPoster_path());
        values.put(MovieEntry.RUNTIME, movie.getRuntime());
        values.put(MovieEntry.STATUS, movie.getStatus());
        values.put(MovieEntry.ADULT, movie.isAdult());
        values.put(MovieEntry.RELEASE_DATE, movie.getRelease_date());
        values.put(MovieEntry.ORIGINAL_LANGUAGE, movie.getOriginal_language());
        values.put(MovieEntry.REVENUE, movie.getRevenue());
        values.put(MovieEntry.DOWNLOADED, ITEM);

        id = db.update(MovieEntry.TABLE, values, MovieEntry.ID + "=" + movie.getId(), null);
        DatabaseManager.getInstance().closeDatabase();
        return id;
    }

    public static void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(MovieEntry.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public static ArrayList<Movie> getAllMovies() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ArrayList<Movie> movies = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + MovieEntry.TABLE, null);

        if (c.moveToFirst())
            do {
                Movie movie = new Movie();
                movie.setId(c.getInt(ID));
                movie.setTitle(c.getString(TITLE));
                movie.setOriginal_title(c.getString(ORIGINAL_TITLE));
                movie.setOverview(c.getString(OVERVIEW));
                movie.setBackdrop_path(c.getString(BACKDROP_PATH));
                movie.setPoster_path(c.getString(POSTER_PATH));
                movie.setAdult(c.getInt(ADULT) > 0);
                movie.setRelease_date(c.getString(RELEASE_DATE));
                movie.setOriginal_language(c.getString(ORIGINAL_LANGUAGE));
                movies.add(movie);
            } while (c.moveToNext());
        c.close();
        DatabaseManager.getInstance().closeDatabase();
        return movies;
    }

    public static Movie getMovieById(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + MovieEntry.TABLE +
                " WHERE " + MovieEntry.ID + "=" + id + " AND " + MovieEntry.DOWNLOADED + "=" + ITEM, null);
        Movie movie = new Movie();
        if (c.moveToFirst()) {
            movie = new Movie();
            movie.setId(c.getInt(ID));
            movie.setTitle(c.getString(TITLE));
            movie.setOriginal_title(c.getString(ORIGINAL_TITLE));
            movie.setOverview(c.getString(OVERVIEW));
            movie.setHomepage(c.getString(HOMEPAGE));
            movie.setBudget(c.getInt(BUDGET));
            movie.setBackdrop_path(c.getString(BACKDROP_PATH));
            movie.setPoster_path(c.getString(POSTER_PATH));
            movie.setRuntime(c.getInt(RUNTIME));
            movie.setStatus(c.getString(STATUS));
            movie.setAdult(c.getInt(ADULT) > 0);
            movie.setRelease_date(c.getString(RELEASE_DATE));
            movie.setOriginal_language(c.getString(ORIGINAL_LANGUAGE));
            movie.setRevenue(c.getInt(REVENUE));
        }
        c.close();
        DatabaseManager.getInstance().closeDatabase();
        if (movie.getId() == 0){
            return null;
        }
        return movie;
    }
}