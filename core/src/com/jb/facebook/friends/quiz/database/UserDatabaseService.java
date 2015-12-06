package com.jb.facebook.friends.quiz.database;

import com.jb.facebook.friends.quiz.friends.UsersJson;

/**
 * Created by brekol on 06.12.15.
 */
public class UserDatabaseService extends JsonDatabaseService<UsersJson> {

    private static final String USERS_JSON_KEY = "USERS_JSON_KEY";

    public UserDatabaseService() {
        super(UsersJson.class);
    }

    public void saveUsers(final UsersJson usersJson) {
        saveJson(USERS_JSON_KEY, usersJson);
    }

    public UsersJson getUsers() {
        return getJson(USERS_JSON_KEY);
    }
}
