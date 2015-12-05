package com.jb.facebook.friends.quiz.database;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by brekol on 05.12.15.
 */
public class AbstractPreferencesService {

    private static final String PREFERENCES_NAME = "facebookFriendsQuiz1";

    private final Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

    protected void saveKey(final String key, final String value){
        preferences.putString(key,value);
        preferences.flush();
    }

    protected String getString(final String key){
        return preferences.getString(key);
    }

}
