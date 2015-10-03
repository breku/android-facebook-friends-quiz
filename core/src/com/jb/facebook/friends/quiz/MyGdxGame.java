package com.jb.facebook.friends.quiz;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jb.facebook.friends.quiz.callback.FacebookLoginCallback;
import com.jb.facebook.friends.quiz.callback.GetFriendsGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookConfig;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebookSystem;

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


        gdxFacebook.loginWithReadPermissions(permissions, new FacebookLoginCallback());

        GDXFacebookGraphRequest request = new GDXFacebookGraphRequest();
        request.setNode("me/friends"); // call against the "me" node.
        request.useCurrentAccessToken(); // Set this if you want gdx-facebook to use the currently cached access token.

        gdxFacebook.newGraphRequest(request, new GetFriendsGraphRequest());

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
