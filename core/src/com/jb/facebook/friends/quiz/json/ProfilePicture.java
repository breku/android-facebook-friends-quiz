package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brekol on 06.12.15.
 */
public class ProfilePicture {

    @SerializedName("data")
    private ProfilePictureData profilePictureData;

    public ProfilePictureData getProfilePictureData() {
        return profilePictureData;
    }

    public void setProfilePictureData(ProfilePictureData profilePictureData) {
        this.profilePictureData = profilePictureData;
    }
}

