package com.jb.facebook.friends.quiz.friends;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brekol on 05.12.15.
 */
public class UserDetails {

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
