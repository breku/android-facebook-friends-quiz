package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.database.UserDatabaseService;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.json.UsersJson;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by brekol on 06.12.15.
 */
public class GameService {

    private final GDXFacebook gdxFacebook;
    private final UserDatabaseService userDatabaseService = new UserDatabaseService();

    private static final String TAG = "GameService";

    @Inject
    public GameService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public void initializeUsers(CallbackListener initializeUsersListener) {
        Gdx.app.log(TAG, "#initializeUsers<< Service called");
        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode("me/friends").putField("fields", "picture,name").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new InitializeUsersCallback(initializeUsersListener));
        Gdx.app.log(TAG, "#initializeUsers<< Service finished");
    }

    public List<UserDetails> getUserDetailsList() {
        final UsersJson users = userDatabaseService.getUsers();
        return users.getUserDetailsList();
    }
}
