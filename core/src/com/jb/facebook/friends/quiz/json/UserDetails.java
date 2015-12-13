package com.jb.facebook.friends.quiz.json;

import com.google.gson.annotations.SerializedName;

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

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("picture")
    private ProfilePicture profilePicture;

    @SerializedName("music")
    private PictureObject music;

    @SerializedName("movies")
    private PictureObject movies;

    @SerializedName("books")
    private PictureObject books;

    public PictureObject getBooks() {
        return books;
    }

    public void setBooks(PictureObject books) {
        this.books = books;
    }

    public PictureObject getMusic() {
        return music;
    }

    public void setMusic(PictureObject music) {
        this.music = music;
    }

    public PictureObject getMovies() {
        return movies;
    }

    public void setMovies(PictureObject movies) {
        this.movies = movies;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

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
