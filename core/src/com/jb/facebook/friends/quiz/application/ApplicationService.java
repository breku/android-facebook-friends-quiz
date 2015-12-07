package com.jb.facebook.friends.quiz.application;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.configuration.FacebookConfig;
import com.jb.facebook.friends.quiz.database.UserPreferencesService;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by brekol on 05.12.15.
 */
public class ApplicationService {
    private static final String TAG = "ApplicationService";
    private final GDXFacebook gdxFacebook;
    private final UserPreferencesService userPreferencesService = new UserPreferencesService();

    public ApplicationService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public void updateApplicationAccessToken() {

        Gdx.app.log(TAG, ">> Service called");

        final String applicationAccessTokenKey = userPreferencesService.getApplicationAccessTokenKey();
        if (StringUtils.isNotBlank(applicationAccessTokenKey)) {
            Gdx.app.log(TAG, "<< Got application access token from shared preferences");
            Gdx.app.log(TAG, ">> Service finished");
            return;
        } else {
            Gdx.app.log(TAG, ">> Token from shared preferences is empty. Making a call.");
            final GDXFacebookGraphRequest appAccesTokenRequest = new GDXFacebookGraphRequest();
            appAccesTokenRequest.putField("client_id", FacebookConfig.APPLICATION_ID);
            appAccesTokenRequest.putField("grant_type", "client_credentials");
            appAccesTokenRequest.putField("client_secret", FacebookConfig.APPLICATION_SECRET);
            appAccesTokenRequest.setNode("/oauth/access_token");

            final ApplicationAccessTokenCallback accessTokenCallback = new ApplicationAccessTokenCallback();
            gdxFacebook.newGraphRequest(appAccesTokenRequest, accessTokenCallback);
            Gdx.app.log(TAG, "<< Service finished");
        }
    }
}