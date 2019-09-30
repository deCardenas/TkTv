package com.aprendiendo.tktv.api;

import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.data.Person;
import com.aprendiendo.tktv.data.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public interface Service {
    interface People{
        @GET("person/popular")
        Call<List<Person>> getPopularPeople(@Query("api_key")String apiKey, @Query("language")String language, @Query("page")int page);
        @GET("search/person")
        Call<List<Person>> getSearchedPeople(@Query("api_key")String apiKey, @Query("language")String language, @Query("page")int page, @Query("query")String query);
        @GET("person/{person_id}")
        Call<Person> getPersonDetail(@Path("person_id")String id, @Query("api_key")String apiKey, @Query("language")String language);
        @GET("person/changes")
        Call<List> getPersonChanges(@Query("api_key")String apiKey);
    }
    interface TvShows{
        @GET("tv/popular")
        Call<List<TvShow>> getPopularTvShows(@Query("api_key")String apiKey, @Query("language")String language, @Query("page")int page);
        @GET("search/tv")
        Call<List<TvShow>> getSearchedTvShows(@Query("api_key")String apiKey, @Query("language")String language, @Query("page")int page, @Query("query")String query);
        @GET("tv/{tv_id}")
        Call<TvShow> getTvShowDetail(@Path("tv_id")String id, @Query("api_key")String apiKey, @Query("language")String language);
        @GET("tv/changes")
        Call<List> getTvShowChanges(@Query("api_key")String apiKey);
    }
    interface Movies{
        @GET("movie/popular")
        Call<List<Movie>> getPopularMovies(@Query("api_key")String apiKey, @Query("language")String language, @Query("page")int page);
        @GET("search/movie")
        Call<List<Movie>> getSearchedMovies(@Query("api_key")String apiKey, @Query("language")String language, @Query("page")int page, @Query("query")String query);
        @GET("movie/{movie_id}")
        Call<Movie> getMovieDetail(@Path("movie_id")String id, @Query("api_key")String apiKey, @Query("language")String language);
        @GET("movie/changes")
        Call<List> getMovieChanges(@Query("api_key")String apiKey);
    }
}
