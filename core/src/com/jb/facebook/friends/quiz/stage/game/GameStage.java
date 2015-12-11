package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenManager;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.game.image.ImageService;
import com.jb.facebook.friends.quiz.stage.invite.model.RefreshButton;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brekol on 06.12.15.
 */
public class GameStage extends AbstractStage {

    private final GDXFacebook gdxFacebook;
    private RefreshButton refreshButton;
    private BackButton backButton;
    private GameService gameService;
    private CallbackListener initializeUsersListener = new CallbackListener();
    private BitmapFont font;

    @Inject
    ImageService imageService = new ImageService();

    private List<UserRow> userRows = new ArrayList<UserRow>();

    public GameStage(final GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
        createButtons();
        initialize();
    }

    @Override
    public void draw() {
        super.draw();
        if (refreshButton.isClicked()) {
            refreshButton.setClicked(false);
        }

        if (initializeUsersListener.isCallbackSucceed()) {
            initializeUsersListener.setCallbackSucceed(false);
            final List<UserDetails> userDetailsList = gameService.getUserDetailsList();
            for (int i = 0; i < userDetailsList.size(); i++) {

                userRows.add(new UserRow(imageService, userDetailsList.get(i), i));
            }
            for (UserRow userRow : userRows) {
                addActor(userRow);
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

    private void initialize() {
        gameService = new GameService(gdxFacebook);
        gameService.initializeUsers(initializeUsersListener);
        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" +
                ".png"), false);
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
