package com.jb.facebook.friends.quiz.stage.pregame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;
import com.jb.facebook.friends.quiz.stage.invite.model.RefreshButton;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jb.facebook.friends.quiz.configuration.ContextConstants.USER_ID_ADDITIONAL_DATA_KEY;

/**
 * Created by brekol on 06.12.15.
 */
public class PreGameStage extends AbstractStage {

    private final GameService gameService;
    private final ImageService imageService;
    private RefreshButton refreshButton;
    private BackButton backButton;
    private BitmapFont font;
    private List<UserRow> userRows = new ArrayList<>();

    @Inject
    public PreGameStage(final GameService gameService, final ImageService imageService) {
        this.gameService = gameService;
        this.imageService = imageService;
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (refreshButton.isClicked()) {
            refreshButton.setClicked(false);
        }

        for (UserRow userRow : userRows) {
            if (userRow.isClicked()) {
                userRow.setClicked(false);

                additionalData.put(USER_ID_ADDITIONAL_DATA_KEY, userRow.getUserId());
                setTargetScreenType(ScreenType.GAME);
            }
        }

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

    @Override
    public void disposeStage() {
        backButton.remove();
        refreshButton.remove();
        for (UserRow userRow : userRows) {
            userRow.remove();
        }
    }

    @Override
    public void initialize() {
        createButtons();
        initializeFont();
        initializeUsers();
    }


    private void initializeUsers() {
        final List<UserDetails> userDetailsList = gameService.getUserDetailsList();
        for (int i = 0; i < userDetailsList.size(); i++) {

            userRows.add(new UserRow(gameService, imageService, userDetailsList.get(i), i));
        }
        for (UserRow userRow : userRows) {
            addActor(userRow);
        }
    }

    private void initializeFont() {
        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" + ".png"), false);
        font.setColor(Color.BLACK);
    }

    private void returnToMenu() {
        setTargetScreenType(ScreenType.MENU);
    }

    private void createButtons() {
        refreshButton = new RefreshButton();
        backButton = new BackButton();

        addActor(backButton);
        addActor(refreshButton);
    }
}
