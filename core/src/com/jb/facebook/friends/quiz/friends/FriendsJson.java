package com.jb.facebook.friends.quiz.friends;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brekol on 05.12.15.
 */
public class FriendsJson {

    @SerializedName("data")
    private List<UserDetails> userDetailsList;

    public List<UserDetails> getUserDetailsList() {
        return userDetailsList;
    }

    public void setUserDetailsList(List<UserDetails> userDetailsList) {
        this.userDetailsList = userDetailsList;
    }
}
