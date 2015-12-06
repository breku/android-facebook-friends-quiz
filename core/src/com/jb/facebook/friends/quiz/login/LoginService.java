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
        permissions.add("user_friends");
        permissions.add("user_likes");
        permissions.add("user_photos");
        permissions.add("user_about_me");
        return permissions;
    }
}
