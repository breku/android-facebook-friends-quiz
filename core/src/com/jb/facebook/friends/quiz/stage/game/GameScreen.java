package com.jb.facebook.friends.quiz.stage.game;

import com.jb.facebook.friends.quiz.stage.AbstractScreen;

import javax.inject.Inject;

/**
 * Created by brekol on 12.12.15.
 */
public class GameScreen extends AbstractScreen {

    @Inject
    protected GameScreen(GameStage stage) {
        super(stage);
    }
}
