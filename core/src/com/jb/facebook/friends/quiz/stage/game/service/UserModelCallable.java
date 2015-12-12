package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.game.model.UserModel;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 12.12.15.
 */
public class UserModelCallable extends AbstractFacebookCallable<UserModel> {

    public UserModelCallable(final GDXFacebook gdxFacebook, final String userId) {
        super(gdxFacebook, userId, "music{picture.type(large),name}");
    }

    @Override
    public UserModel call() throws Exception {
        initializeResult();
        final UserDetails userDetails = new Gson().fromJson(jsonResult, UserDetails.class);
        return new UserModel();
    }
}
