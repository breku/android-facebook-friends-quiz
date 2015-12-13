package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by brekol on 06.12.15.
 */
public class GameService {

    private static final String TAG = "GameService";
    private static final String MY_FRIENDS_CONSTANT_KEY = "myFriendsConstantKey";
    private static final CacheBuilder cacheBuilder = CacheBuilder.newBuilder().maximumSize(200).expireAfterWrite(100, TimeUnit
            .MINUTES);
    private final GDXFacebook gdxFacebook;
    private Cache<String, UserDetails> userDetailsCache = cacheBuilder.build();
    private Cache<String, List<UserDetails>> friendListCache = cacheBuilder.build();

    @Inject
    public GameService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public List<UserDetails> getUserDetailsList() {
        try {
            return friendListCache.get(MY_FRIENDS_CONSTANT_KEY, new GetFriendsCallable(gdxFacebook));
        } catch (ExecutionException e) {
            Gdx.app.log(TAG, "Error during getting user friend list", e);
        }
        return Collections.emptyList();
    }

    public UserDetails getUserDetails(String userId) {
        try {
            return userDetailsCache.get(userId, new UserDetailsCallable(gdxFacebook, userId));
        } catch (ExecutionException e) {
            Gdx.app.log(TAG, "Error during getting user model", e);
        }
        return null;
    }

    public UserDetails getMyUserDetails() {
        return getUserDetails("me");
    }
}
