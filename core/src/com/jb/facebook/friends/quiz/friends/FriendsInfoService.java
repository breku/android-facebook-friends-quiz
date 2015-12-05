package com.jb.facebook.friends.quiz.friends;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.application.ApplicationService;
import com.jb.facebook.friends.quiz.callback.DefaultJsonFacebookCallback;
import com.jb.facebook.friends.quiz.configuration.FacebookConfig;
import com.jb.facebook.friends.quiz.database.UserPreferencesService;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;

/**
 * Created by brekol on 05.12.15.
 */
public class FriendsInfoService {
    private static final String TAG = "FriendsInfoService";
    private final GDXFacebook gdxFacebook;


    private final UserPreferencesService userPreferencesService;
    public FriendsInfoService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
        this.userPreferencesService = new UserPreferencesService();
    }

    public void getFriendsInfo() {

        Gdx.app.log(TAG, ">> Service called");
        getAppUsersIds();

        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode("105549556483258").putField("access_token", userPreferencesService.getApplicationAccessTokenKey());

        gdxFacebook.newGraphRequest(request, new DefaultJsonFacebookCallback());

        Gdx.app.log(TAG, "<< Service finished");
    }

    private void getAppUsersIds() {
        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode(FacebookConfig.APPLICATION_ID + "/accounts/test-users").putField("access_token", userPreferencesService.getApplicationAccessTokenKey());

        gdxFacebook.newGraphRequest(request, new GetFriendsCallback());
    }
}
