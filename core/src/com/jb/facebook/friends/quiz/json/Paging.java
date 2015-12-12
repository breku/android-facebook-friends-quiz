package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brekol on 12.12.15.
 */
public class Paging {

    @SerializedName("next")
    private String nextPageUrl;

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }
}
