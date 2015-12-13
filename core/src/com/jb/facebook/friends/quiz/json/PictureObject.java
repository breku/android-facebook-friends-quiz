package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brekol on 13.12.15.
 */
public class PictureObject {

    @SerializedName("data")
    private List<PictureData> pictureDataList;

    public List<PictureData> getPictureDataList() {
        return pictureDataList;
    }

    public void setPictureDataList(List<PictureData> pictureDataList) {
        this.pictureDataList = pictureDataList;
    }
}
