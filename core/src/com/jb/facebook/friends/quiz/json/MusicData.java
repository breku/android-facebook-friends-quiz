package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by brekol on 12.12.15.
 */
public class MusicData {

    @SerializedName("created_time")
    private Date createdTime;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
