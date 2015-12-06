package com.jb.facebook.friends.quiz.database;

/**
 * Created by brekol on 05.12.15.
 */
public class UserPreferencesService extends AbstractPreferencesService {

    private static final String APPLICATION_ACCESS_TOKEN_KEY = "APPLICATION_ACCESS_TOKEN_KEY";

    public void saveApplicationAccessToken(final String value) {
        saveString(APPLICATION_ACCESS_TOKEN_KEY, value);
    }

    public String getApplicationAccessTokenKey() {
        return getString(APPLICATION_ACCESS_TOKEN_KEY);
    }
}
