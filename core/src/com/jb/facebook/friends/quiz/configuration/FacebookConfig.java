package com.jb.facebook.friends.quiz.configuration;

import de.tomgrill.gdxfacebook.core.GDXFacebookConfig;

/**
 * Created by brekol on 05.12.15.
 */
public class FacebookConfig extends GDXFacebookConfig {

    public static final String APPLICATION_ID = "1289355597747909";
    public static final String APPLICATION_SECRET = "0645298d1ca5decbc438d198e59373f4";

    public FacebookConfig() {
        PREF_FILENAME = ".asdfasfd"; // optional
        APP_ID = APPLICATION_ID; // required
    }
}
