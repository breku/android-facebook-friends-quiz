package com.jb.facebook.friends.quiz.database;

import com.google.gson.Gson;

/**
 * Created by brekol on 06.12.15.
 */
public class JsonDatabaseService<T> extends AbstractPreferencesService {

    private final Gson gson = new Gson();
    private final Class<T> type;

    public JsonDatabaseService(Class<T> type) {
        this.type = type;
    }

    protected void saveJson(final String key, final Object jsonObject) {
        final String jsonToSave = gson.toJson(jsonObject);
        saveString(key, jsonToSave);
    }

    protected T getJson(final String key) {
        final String jsonString = getString(key);
        return gson.fromJson(jsonString, type);
    }
}
