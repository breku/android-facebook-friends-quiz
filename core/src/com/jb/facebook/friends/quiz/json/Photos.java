package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brekol on 06.12.15.
 */
public class Photos {

    @SerializedName("data")
    private List<PhotoData> photoDataList;

    public List<PhotoData> getPhotoDataList() {
        return photoDataList;
    }

    public void setPhotoDataList(List<PhotoData> photoDataList) {
        this.photoDataList = photoDataList;
    }
}
