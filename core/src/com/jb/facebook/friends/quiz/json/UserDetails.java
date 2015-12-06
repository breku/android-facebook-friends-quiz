package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brekol on 05.12.15.
 */
public class UserDetails {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("photos")
    private Photos photos;

    @SerializedName("picture")
    private ProfilePicture profilePicture;

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
