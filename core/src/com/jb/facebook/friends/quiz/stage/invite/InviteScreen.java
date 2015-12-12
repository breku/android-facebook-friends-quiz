package com.jb.facebook.friends.quiz.stage.invite;

import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import com.jb.facebook.friends.quiz.stage.ScreenType;

import javax.inject.Inject;

/**
 * Created by brekol on 06.12.15.
 */
public class InviteScreen extends AbstractScreen {
    private static final String TAG = "InviteScreen";

    @Inject
    public InviteScreen(InviteStage inviteStage) {
        super(inviteStage);
    }

    @Override
    public ScreenType getTargetScreenType() {
        return stage.getTargetScreenType();
    }
}
