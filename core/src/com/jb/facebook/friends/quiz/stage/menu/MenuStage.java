package com.jb.facebook.friends.quiz.stage.menu;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.friends.FriendsInfoService;
import com.jb.facebook.friends.quiz.stage.menu.button.*;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 05.12.15.
 */
public class MenuStage extends AbstractStage {

    private final FriendsInfoService friendsInfoService;
    private MenuButton playButton;
    private MenuButton optionsButton;
    private MenuButton inviteButton;
    private MenuButton exitButton;

    public MenuStage(GDXFacebook gdxFacebook) {
        friendsInfoService = new FriendsInfoService(gdxFacebook);
        playButton = new PlayButton();
        optionsButton = new SettingsButton();
        inviteButton = new InviteButton();
        exitButton = new ExitButton();
        addActor(playButton);
        addActor(optionsButton);
        addActor(inviteButton);
        addActor(exitButton);
    }

    @Override
    public void act() {
        super.act();
        Gdx.app.log(TAG, "Act");

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(playButton.isClicked()){
            playButton.setClicked(false);
            friendsInfoService.getFriendsInfo();
        }

    }

    private static final String TAG = "MainMenuScene";
    @Override
    public void draw() {
        super.draw();
    }
}
