package com.jb.facebook.friends.quiz.login;

import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookLoginResult;

/**
 * Created by brekol on 03.10.15.
 */
public class FacebookLoginCallback implements GDXFacebookCallback<GDXFacebookLoginResult> {

    private static final String TAG = "FacebookLoginCallback";
    private String userId;

    @Override
    public void onSuccess(GDXFacebookLoginResult result) {
        Gdx.app.log(TAG, "Result token=" + result.getAccessToken().getToken());
        userId = result.getAccessToken().getUserId();
    }

    @Override
    public void onError(GDXFacebookError error) {
        Gdx.app.log(TAG, String.format("Login finished with error code=%s type=%s message=%s", error.getErrorCode(), error.getErrorType(), error.getErrorMessage()));
    }

    @Override
    public void onFail(Throwable t) {
        Gdx.app.log(TAG, "fail " + t.getMessage(), t);
    }

    @Override
    public void onCancel() {

        Gdx.app.log(TAG, "cancel");
    }

    public String getUserId() {
        return userId;
    }
}
