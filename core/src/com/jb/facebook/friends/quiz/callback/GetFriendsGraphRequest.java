package com.jb.facebook.friends.quiz.callback;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 03.10.15.
 */
public class GetFriendsGraphRequest implements GDXFacebookCallback<GDXFacebookGraphResult> {
    private static final String TAG = "GetFriendsGraphRequest";

    @Override
    public void onSuccess(GDXFacebookGraphResult result) {
        Gdx.app.log(TAG, "Result: " + result.getResultAsJson());
        JsonValue root = new JsonReader().parse(result.getResultAsJson());
        // In a real project you should do some validation of the JsonValue. You never know what comes back :)
        String fbID = root.getString("id");
        String fbNickname = root.getString("name");
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
