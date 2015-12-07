package com.jb.facebook.friends.quiz.stage.game;

import com.jb.facebook.friends.quiz.database.UserDatabaseService;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.json.UsersJson;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;

import java.util.List;

/**
 * Created by brekol on 06.12.15.
 */
public class GameService {

    private final GDXFacebook gdxFacebook;
    private final UserDatabaseService userDatabaseService = new UserDatabaseService();

    public GameService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public void initializeUsers(CallbackListener initializeUsersListener) {
        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode("me/friends").putField("fields", "picture,name").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new InitializeUsersCallback(initializeUsersListener));
    }

    public List<UserDetails> getUserDetailsList() {
        final UsersJson users = userDatabaseService.getUsers();
        return users.getUserDetailsList();
    }
}
