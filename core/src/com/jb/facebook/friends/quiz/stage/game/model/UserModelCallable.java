package com.jb.facebook.friends.quiz.stage.game.model;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.callback.AbstractFacebookCallback;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by brekol on 12.12.15.
 */
public class UserModelCallable implements Callable<UserModel> {

    private static final String TAG = "UserModelCallable";
    private final GDXFacebook gdxFacebook;
    private final String userId;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private UserModel result;

    public UserModelCallable(final GDXFacebook gdxFacebook, final String userId) {
        this.gdxFacebook = gdxFacebook;
        this.userId = userId;
    }

    public void initializeUserModel() {
        Gdx.app.log(TAG, "#initializeUserMode>> Service called");

        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode(userId).putField("fields", "music{picture.type(large),name}").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new AbstractFacebookCallback() {
            @Override
            public void onSuccess(GDXFacebookGraphResult result) {
                Gdx.app.log(TAG, "#initializeUserModel result=" + result.getResultAsJson());
                final UserDetails userDetails = new Gson().fromJson(result.getResultAsJson(), UserDetails.class);

                UserModelCallable.this.result = new UserModel();
                countDownLatch.countDown();
            }
        });
        Gdx.app.log(TAG, "#initializeUserModel<< Service finished");
    }

    @Override
    public UserModel call() throws Exception {
        initializeUserModel();
        countDownLatch.await(5L, TimeUnit.SECONDS);
        return result;
    }
}
