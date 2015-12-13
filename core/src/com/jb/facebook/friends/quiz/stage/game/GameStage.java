package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;

/**
 * Created by brekol on 12.12.15.
 */
public class GameStage extends AbstractStage {

    private final GameService gameService;
    private final ImageService imageService;
    private UserDetails userDetails;

    private BackButton backButton;

    @Inject
    public GameStage(GameService gameService, ImageService imageService) {
        this.gameService = gameService;
        this.imageService = imageService;
    }

    @Override
    public void initialize() {
        createButtons();
        final String userId = (String) additionalData.get(ContextConstants.USER_ID_ADDITIONAL_DATA_KEY);
        userDetails = gameService.getUserDetails(userId);

        Question question = new Question(imageService,userDetails);
        addActor(question);

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
