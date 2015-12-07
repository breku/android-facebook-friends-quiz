package com.jb.facebook.friends.quiz.stage.menu;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.common.ToastWindow;
import com.jb.facebook.friends.quiz.stage.game.GameScreen;
import com.jb.facebook.friends.quiz.stage.invite.InviteScreen;
import com.jb.facebook.friends.quiz.stage.menu.button.*;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 05.12.15.
 */
public class MenuStage extends AbstractStage {

    private static final String TAG = "MainMenuScene";
    final GDXFacebook gdxFacebook;
    final MyGdxGame myGdxGame;
    private AbstractMenuButton playButton;
    private AbstractMenuButton optionsButton;
    private AbstractMenuButton inviteButton;
    private AbstractMenuButton exitButton;
    private ToastWindow toastWindow;

    public MenuStage(final MyGdxGame myGdxGame, final GDXFacebook gdxFacebook) {
        this.myGdxGame = myGdxGame;
        this.gdxFacebook = gdxFacebook;
        createButtons();
        createToastWindow();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (playButton.isClicked()) {
            playButton.setClicked(false);

            if (gdxFacebook.isLoggedIn()) {
                myGdxGame.setScreen(new GameScreen(myGdxGame, gdxFacebook));
            } else {
                toastWindow.showToast("You have to sign in to your facebook account\nto play.");
            }
        }

        if (inviteButton.isClicked()) {
            inviteButton.setClicked(false);
            myGdxGame.setScreen(new InviteScreen(myGdxGame, gdxFacebook));
        }

        if (exitButton.isClicked()) {
            exitButton.setClicked(false);
            Gdx.app.exit();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.exit();
        return true;
    }

    private void createToastWindow() {
        toastWindow = new ToastWindow();
        addActor(toastWindow);
    }

    private void createButtons() {
        playButton = new PlayButton();
        optionsButton = new SettingsButton();
        inviteButton = new InviteButton();
        exitButton = new ExitButton();

        addActor(playButton);
        addActor(optionsButton);
        addActor(inviteButton);
        addActor(exitButton);
    }
}
