package com.jb.facebook.friends.quiz.friends;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 05.12.15.
 */
public class GetFriendsCallback implements GDXFacebookCallback<GDXFacebookGraphResult> {


    private static final String TAG = "GetFriendsGraphRequest";


    @Override
    public void onSuccess(GDXFacebookGraphResult result) {
        final FriendsJson friendsJson = new Gson().fromJson(result.getResultAsJson(), FriendsJson.class);


        Gdx.app.log(TAG, "Result=" + result.getResultAsJson());
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
