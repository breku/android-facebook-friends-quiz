package com.jb.facebook.friends.quiz.stage.game.service;

import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.callback.AbstractFacebookCallback;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

import javax.inject.Inject;

/**
 * Created by brekol on 14.12.15.
 */
public class AsyncGameService {

    private final GDXFacebook gdxFacebook;

    @Inject
    public AsyncGameService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public UserDetails getUserDetails(){
        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode("774787122627392").putField("fields", "name,id").putField("limit","100").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new AbstractFacebookCallback() {
            @Override
            public void onSuccess(GDXFacebookGraphResult result) {

                String jsonResult = result.getResultAsJson();
                UserDetails asynchronousResult = new Gson().fromJson(jsonResult, UserDetails.class);
            }
        });
        return null;
    }


}
