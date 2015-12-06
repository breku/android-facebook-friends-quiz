package com.jb.facebook.friends.quiz.stage.menu;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.game.GameScreen;
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

    public MenuStage(final MyGdxGame myGdxGame, final GDXFacebook gdxFacebook) {
        this.myGdxGame = myGdxGame;
        this.gdxFacebook = gdxFacebook;
        createButtons();
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
            myGdxGame.setScreen(new GameScreen(myGdxGame, gdxFacebook));
        }

        if (exitButton.isClicked()) {
            exitButton.setClicked(false);
            Gdx.app.exit();
        }
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
