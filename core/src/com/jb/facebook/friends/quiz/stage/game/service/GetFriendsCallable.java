package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.json.UsersJson;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

import java.util.List;

/**
 * Created by brekol on 12.12.15.
 */
public class GetFriendsCallable extends AbstractFacebookCallable<List<UserDetails>> {

    public GetFriendsCallable(final GDXFacebook gdxFacebook) {
        super(gdxFacebook, "me/friends", "picture.type(large),name");
    }

    @Override
    public List<UserDetails> call() throws Exception {
        initializeResult();
        final UsersJson usersJson = new Gson().fromJson(jsonResult, UsersJson.class);
        return usersJson.getUserDetailsList();
    }
}
