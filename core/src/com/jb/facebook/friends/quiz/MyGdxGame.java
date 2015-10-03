package com.jb.facebook.friends.quiz;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.tomgrill.gdxfacebook.core.*;

import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    private static final String TAG = "MyGdxGame";

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        GDXFacebookConfig config = new GDXFacebookConfig();
        config.PREF_FILENAME = ".asdfasfd"; // optional
        config.APP_ID = "1289355597747909"; // required

        GDXFacebook gdxFacebook = GDXFacebookSystem.install(config);

        List<String> permissions = new ArrayList<String>();
        permissions.add("email");
        permissions.add("public_profile");
        permissions.add("user_friends");

        gdxFacebook.loginWithReadPermissions(permissions, new GDXFacebookCallback<GDXFacebookLoginResult>() {
            @Override
            public void onSuccess(GDXFacebookLoginResult result) {
                Gdx.app.log(TAG, "Result token" + result.getAccessToken().getToken());
            }

            @Override
            public void onError(GDXFacebookError error) {
                Gdx.app.log(TAG, "ERROR" + error);
            }

            @Override
            public void onCancel() {

                Gdx.app.log(TAG, "cancel");
            }

            @Override
            public void onFail(Throwable t) {
                Gdx.app.log(TAG, "fail " + t.getMessage(), t);
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }
}
