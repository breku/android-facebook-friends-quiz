package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brekol on 06.12.15.
 */
public class ProfilePictureData {

    @SerializedName("is_silhouette")
    private boolean isBlank;

    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setBlank(boolean blank) {
        isBlank = blank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
