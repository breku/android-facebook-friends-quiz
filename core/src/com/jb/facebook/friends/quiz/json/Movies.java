package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brekol on 13.12.15.
 */
public class Movies {

    @SerializedName("data")
    private List<MoviesData> moviesDataList;

    public List<MoviesData> getMoviesDataList() {
        return moviesDataList;
    }

    public void setMoviesDataList(List<MoviesData> moviesDataList) {
        this.moviesDataList = moviesDataList;
    }
}
