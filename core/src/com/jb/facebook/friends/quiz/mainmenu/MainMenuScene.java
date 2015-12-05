package com.jb.facebook.friends.quiz.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.jb.facebook.friends.quiz.friends.FriendsInfoService;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 05.12.15.
 */
public class MainMenuScene extends Stage {

    private final FriendsInfoService friendsInfoService;
    private PlayButton playButton;

    public MainMenuScene(GDXFacebook gdxFacebook) {
        super();
        friendsInfoService = new FriendsInfoService(gdxFacebook);

        playButton = new PlayButton();
        playButton.setTouchable(Touchable.enabled);
        addActor(playButton);
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
