package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 12.12.15.
 */
public class UserDetailsCallable extends AbstractFacebookCallable<UserDetails> {

    public UserDetailsCallable(final GDXFacebook gdxFacebook, final String userId) {
        super(gdxFacebook, userId, "name,music{picture.height(600).width(600){height,width,is_silhouette,url},name}");
    }

    @Override
    public UserDetails call() throws Exception {
        initializeResult();
        return new Gson().fromJson(jsonResult, UserDetails.class);
    }
}
