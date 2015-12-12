package com.jb.facebook.friends.quiz.stage.pregame;

import com.badlogic.gdx.Gdx;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jb.facebook.friends.quiz.database.UserDatabaseService;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.json.UsersJson;
import com.jb.facebook.friends.quiz.stage.game.model.UserModel;
import com.jb.facebook.friends.quiz.stage.game.model.UserModelCallable;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by brekol on 06.12.15.
 */
public class GameService {

    private static final String TAG = "GameService";
    private final GDXFacebook gdxFacebook;
    private final UserDatabaseService userDatabaseService = new UserDatabaseService();
    private Cache<String, UserModel> cache = CacheBuilder.newBuilder().maximumSize(200).expireAfterWrite(100, TimeUnit
            .MINUTES).build();

    @Inject
    public GameService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public void initializeUsers(CallbackListener initializeUsersListener) {
        Gdx.app.log(TAG, "#initializeUsers<< Service called");
        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode("me/friends").putField("fields", "picture.type(large),name").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new InitializeUsersCallback(initializeUsersListener));
        Gdx.app.log(TAG, "#initializeUsers<< Service finished");
    }

    public List<UserDetails> getUserDetailsList() {
        final UsersJson users = userDatabaseService.getUsers();
        return users.getUserDetailsList();
    }

    public UserModel getUserModel(String userId) {
        try {
            return cache.get(userId, new UserModelCallable(gdxFacebook, userId));
        } catch (ExecutionException e) {
            Gdx.app.log(TAG, "Error during getting user model", e);
        }
        return null;
    }
}
