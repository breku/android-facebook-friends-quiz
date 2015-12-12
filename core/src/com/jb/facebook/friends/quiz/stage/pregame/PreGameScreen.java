package com.jb.facebook.friends.quiz.stage.pregame;

import com.jb.facebook.friends.quiz.stage.AbstractScreen;

import javax.inject.Inject;

/**
 * Created by brekol on 06.12.15.
 */
public class PreGameScreen extends AbstractScreen {


    @Inject
    public PreGameScreen(final PreGameStage preGameStage) {
        super(preGameStage);
    }
}
