package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.callback.AbstractFacebookCallback;
import com.jb.facebook.friends.quiz.database.UserDatabaseService;
import com.jb.facebook.friends.quiz.json.UsersJson;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 06.12.15.
 */
public class InitializeUsersCallback extends AbstractFacebookCallback {
    private static final String TAG = "InitializeUsersCallback";
    private final UserDatabaseService userDatabaseService = new UserDatabaseService();

    private final CallbackListener initializeUsersListener;

    public InitializeUsersCallback(CallbackListener initializeUsersListener) {
        this.initializeUsersListener = initializeUsersListener;
    }

    @Override
    public void onSuccess(GDXFacebookGraphResult result) {
        final UsersJson usersJson = new Gson().fromJson(result.getResultAsJson(), UsersJson.class);
        userDatabaseService.saveUsers(usersJson);
        Gdx.app.log(TAG, "Result=" + result.getResultAsJson());
        initializeUsersListener.setCallbackSucceed(true);
    }
}
