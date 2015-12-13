package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 12.12.15.
 */
public class UserDetailsCallable extends AbstractFacebookCallable<UserDetails> {

    private static final String NODE_NAME = "name";
    private static final String NODE_SEPARATOR = ",";
    private static final String NODE_MUSIC = "music{picture.height(600).width(600){height,width,is_silhouette,url},name}";
    private static final String NODE_VIDEO = "movies{picture.height(600).width(600){height,width,is_silhouette,url},name}";

    public UserDetailsCallable(final GDXFacebook gdxFacebook, final String userId) {
        super(gdxFacebook, userId, Joiner.on(NODE_SEPARATOR).join(NODE_NAME, NODE_MUSIC, NODE_VIDEO));
    }

    @Override
    public UserDetails call() throws Exception {
        initializeResult();
        return new Gson().fromJson(jsonResult, UserDetails.class);
    }
}
