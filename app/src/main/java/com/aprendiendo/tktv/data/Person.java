package com.aprendiendo.tktv.data;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public class Person {
    private static final String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    private String profile_path;
    private boolean adult;
    private int id;
    private String name;
    private Media[] known_for;
    private String[] also_known_as;
    private String biography;
    private String birthday;
    private String deathday;
    private int gender;
    private String homepage;
    private String place_of_birth;

    public String getProfile_path() {
        return IMAGE_URL + profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Media[] getKnown_for() {
        return known_for;
    }

    public void setKnown_for(Media[] known_for) {
        this.known_for = known_for;
    }

    public String[] getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(String[] also_known_as) {
        this.also_known_as = also_known_as;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getKnownFor() {
        StringBuilder builder = new StringBuilder();
        Media media;
        for (int i = 0; i < known_for.length - 1; i++) {
            media = known_for[i];
            if (media.getMedia_type().equals("movie")) {
                if (i == 0)
                    builder.append(media.getTitle());
                else {
                    builder.append(", ");
                    builder.append(media.getTitle());
                }
            }
            if (media.getMedia_type().equals("tv")) {
                if (i == 0)
                    builder.append(media.getName());
                else {
                    builder.append(", ");
                    builder.append(media.getName());
                }
            }
        }
        return builder.toString();
    }

    public String getGenderString() {
        if (gender == 1)
            return "(Mujer)";
        if (gender == 2)
            return "(Hombre)";
        return "";
    }
}
