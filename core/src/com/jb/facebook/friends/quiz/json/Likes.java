package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by brekol on 12.12.15.
 */
public class Likes {

    @SerializedName("paging")
    private Paging paging;

    @SerializedName("data")
    private List<LikeData> likeDataList;

    public List<LikeData> getLikeDataList() {
        return likeDataList;
    }

    public void setLikeDataList(List<LikeData> likeDataList) {
        this.likeDataList = likeDataList;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
