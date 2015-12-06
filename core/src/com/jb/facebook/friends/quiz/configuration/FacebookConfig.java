package com.jb.facebook.friends.quiz.configuration;

import de.tomgrill.gdxfacebook.core.GDXFacebookConfig;

/**
 * Created by brekol on 05.12.15.
 */
public class FacebookConfig extends GDXFacebookConfig {

//    Dane aplikacji testowej
//    public static final String APPLICATION_ID = "1289355597747909";
//    public static final String APPLICATION_SECRET = "0645298d1ca5decbc438d198e59373f4";

//    Dane głównej aplikacji
    public static final String APPLICATION_ID = "1287467634603372";
    public static final String APPLICATION_SECRET = "08cc856a6cdefdbf9fb9d94f1b3e6a04";

    public FacebookConfig() {
        PREF_FILENAME = ".asdfasfd"; // optional
        APP_ID = APPLICATION_ID; // required
    }
}
