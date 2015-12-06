package com.jb.facebook.friends.quiz.stage.menu.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by brekol on 05.12.15.
 */
public class PlayButton extends MenuButton {

    private static final String TAG = "PlayButton";
    public PlayButton() {
        super(new Texture("menu/play.png"), 0, 960);
        Gdx.app.log(TAG, "Height" + Gdx.graphics.getHeight());
        Gdx.app.log(TAG, "Width" + Gdx.graphics.getWidth());
    }
}
