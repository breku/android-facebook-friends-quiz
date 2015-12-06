package com.jb.facebook.friends.quiz.stage.invite;

import com.jb.facebook.friends.quiz.callback.DefaultJsonFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;

/**
 * Created by brekol on 06.12.15.
 */
public class InviteService {

    private final GDXFacebook gdxFacebook;

    public InviteService(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    public void getUsersToInvite(){

        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode("me/friends").putField("fields","likes").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new DefaultJsonFacebookCallback());

    }



}
