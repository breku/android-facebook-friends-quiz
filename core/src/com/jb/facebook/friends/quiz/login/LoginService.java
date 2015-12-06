package com.jb.facebook.friends.quiz.login;

import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brekol on 05.12.15.
 */
public class LoginService {
    private static final String TAG = "LoginService";
    private final GDXFacebook gdxFacebook;

    public LoginService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public void loginToFacebook() {
        Gdx.app.log(TAG, ">> Service called");
        final FacebookLoginCallback facebookLoginCallback = new FacebookLoginCallback();
        gdxFacebook.loginWithReadPermissions(createPermissions(), facebookLoginCallback);
        Gdx.app.log(TAG, "<< Service finished");
    }

    public boolean isUserLoggedToFacebook() {
        Gdx.app.log(TAG, ">> Service called");
        boolean result = gdxFacebook.isLoggedIn();
        Gdx.app.log(TAG, "<< Service finished with result=" + result);
        return result;
    }

    private List<String> createPermissions() {
        List<String> permissions = new ArrayList<String>();
        permissions.add("user_about_me");
        permissions.add("user_actions.books");
        permissions.add("user_actions.fitness");
        permissions.add("user_actions.music");
        permissions.add("user_actions.news");
        permissions.add("user_actions.video");
        permissions.add("user_birthday");
        permissions.add("user_education_history");
        permissions.add("user_events");
        permissions.add("user_friends");
        permissions.add("user_games_activity");
        permissions.add("user_hometown");
        permissions.add("user_likes");
        permissions.add("user_location");
        permissions.add("user_managed_groups");
        permissions.add("user_photos");
        permissions.add("user_posts");
        permissions.add("user_relationship_details");
        permissions.add("user_relationships");
        permissions.add("user_religion_politics");
        permissions.add("user_status");
        permissions.add("user_tagged_places");
        permissions.add("user_videos");
        permissions.add("user_website");
        permissions.add("user_work_history");

        return permissions;
    }
}
