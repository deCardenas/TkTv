package com.aprendiendo.tktv.data;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public class TvShow {
    private static final String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    private String poster_path;
    private int id;
    private String backdrop_path;
    private String overview;
    private String first_air_date;
    private String original_language;
    private String name;
    private String original_name;
    private int[] episode_run_time;
    private String homepage;
    private boolean in_production;
    private String last_air_date;
    private int number_of_episodes;
    private int number_of_seasons;
    private String status;
    private String type;
    private byte[] poster_byte;
    private byte[] backdrop_byte;

    public String getPoster_path() {
        return IMAGE_URL + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return IMAGE_URL + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public int[] getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(int[] episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPoster_byte() {
        return poster_byte;
    }

    public void setPoster_byte(byte[] poster_byte) {
        this.poster_byte = poster_byte;
    }

    public byte[] getBackdrop_byte() {
        return backdrop_byte;
    }

    public void setBackdrop_byte(byte[] backdrop_byte) {
        this.backdrop_byte = backdrop_byte;
    }

    public String getRunTimeFormat() {
        int hours = episode_run_time[0] / 60;
        int minutes = episode_run_time[0] % 60;
        if (hours == 0){
            return minutes + "min";
        }
        return hours + "h " + minutes + "min";
    }
}
