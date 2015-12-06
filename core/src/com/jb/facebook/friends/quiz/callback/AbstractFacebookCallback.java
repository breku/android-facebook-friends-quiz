package com.jb.facebook.friends.quiz.callback;

import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 06.12.15.
 */
public abstract class AbstractFacebookCallback implements GDXFacebookCallback<GDXFacebookGraphResult> {

    private static final String TAG = "AbstractFacebookCallback";

    @Override
    public void onError(GDXFacebookError error) {
        Gdx.app.log(TAG, String.format("Error ocured error code=%s type=%s " +
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
