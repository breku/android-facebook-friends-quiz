package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.common.base.Joiner;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 12.12.15.
 */
public abstract class UserDetailsRequest extends AsyncAbstractFacebookRequest<UserDetails> {

    private static final String NODE_NAME = "name,id";
    private static final String NODE_SEPARATOR = ",";

    private static final String COMMON_PICTURE_PROPERTIES = "{picture.height(600).width(600){height,width,is_silhouette,url},name}";
    private static final String NODE_MUSIC = "music" + COMMON_PICTURE_PROPERTIES;
    private static final String NODE_VIDEO = "movies" + COMMON_PICTURE_PROPERTIES;
    private static final String NODE_BOOKS = "books" + COMMON_PICTURE_PROPERTIES;
    private static final String NODE_LIKES = "likes" + COMMON_PICTURE_PROPERTIES;

    public UserDetailsRequest(final GDXFacebook gdxFacebook, final String userId) {
        super(UserDetails.class,gdxFacebook, userId, Joiner.on(NODE_SEPARATOR).join(NODE_NAME, NODE_MUSIC, NODE_VIDEO, NODE_BOOKS,NODE_LIKES));
        initializeResultAsync();
    }
}
