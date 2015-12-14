package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.json.UsersJson;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

import java.util.List;

/**
 * Created by brekol on 12.12.15.
 */
public abstract class GetFriendsRequest extends AsyncAbstractFacebookRequest<UsersJson> {

    public GetFriendsRequest(final GDXFacebook gdxFacebook) {
        super(UsersJson.class,gdxFacebook, "me/friends", "picture.type(large),name");
        initializeResultAsync();
    }
//
//    public List<UserDetails> call() throws Exception {
//        initializeResult();
//        final UsersJson usersJson = new Gson().fromJson(jsonResult, UsersJson.class);
//        return usersJson.getUserDetailsList();
//    }
}
