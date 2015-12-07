package com.jb.facebook.friends.quiz.callback;

import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 03.10.15.
 */
public class DefaultJsonFacebookCallback implements GDXFacebookCallback<GDXFacebookGraphResult> {

    private static final String TAG = "GetFriendsGraphRequest";

    private String result;

    @Override
    public void onSuccess(GDXFacebookGraphResult result) {
        Gdx.app.log(TAG, "Result=" + result.getResultAsJson());
        this.result = result.getResultAsJson();
    }

    @Override
    public void onError(GDXFacebookError error) {
        Gdx.app.log(TAG, "Error" + error.getErrorMessage());
    }

    @Override
    public void onFail(Throwable t) {
        Gdx.app.log(TAG, "Fail ", t);
    }

    @Override
    public void onCancel() {
        Gdx.app.log(TAG, "Cancel");
    }
}
