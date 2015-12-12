package com.jb.facebook.friends.quiz.stage.game;

import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.game.model.UserModel;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;

import javax.inject.Inject;

/**
 * Created by brekol on 12.12.15.
 */
public class GameStage extends AbstractStage {

    private final GameService gameService;
    private UserModel userModel;

    @Inject
    public GameStage(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void initialize() {
//        userModel = gameService.getUserModel();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
