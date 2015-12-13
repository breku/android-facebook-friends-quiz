package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.game.model.UserModel;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;

import javax.inject.Inject;

/**
 * Created by brekol on 12.12.15.
 */
public class GameStage extends AbstractStage {

    private final GameService gameService;
    private UserModel userModel;

    private BackButton backButton;

    @Inject
    public GameStage(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void initialize() {
        createButtons();
        final String userId = (String) additionalData.get(ContextConstants.USER_ID_ADDITIONAL_DATA_KEY);
        userModel = gameService.getUserModel(userId);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (backButton.isClicked()) {
            backButton.setClicked(false);
            returnToMenu();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            returnToMenu();
            return true;
        }
        return false;
    }

    private void returnToMenu() {
        setTargetScreenType(ScreenType.MENU);
    }

    private void createButtons() {
        backButton = new BackButton();

        addActor(backButton);
    }
}
