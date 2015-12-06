package com.jb.facebook.friends.quiz.application;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.database.UserPreferencesService;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 05.12.15.
 */
public class ApplicationAccessTokenCallback implements GDXFacebookCallback<GDXFacebookGraphResult> {

    private static final String TAG = "ApplicationAccessTokenCallback";

    private final UserPreferencesService userPreferencesService = new UserPreferencesService();

    @Override
    public void onSuccess(GDXFacebookGraphResult result) {
        final String stringResult = result.getResultAsJson();
        final int index = stringResult.indexOf("=");
        final String applicationAccessToken = stringResult.substring(index + 1);
        userPreferencesService.saveApplicationAccessToken(applicationAccessToken);
    }

    @Override
    public void onError(GDXFacebookError error) {
        Gdx.app.log(TAG, String.format("Getting application access token finished with error code=%s type=%s " +
                "message=%s", error.getErrorCode(), error.getErrorType(), error.getErrorMessage()));
    }

    @Override
    public void onFail(Throwable t) {
        Gdx.app.log(TAG, "fail " + t.getMessage(), t);
    }

    @Override
    public void onCancel() {
        Gdx.app.log(TAG, "cancel");
    }
}
