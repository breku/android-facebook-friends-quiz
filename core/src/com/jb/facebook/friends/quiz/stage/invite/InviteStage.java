package com.jb.facebook.friends.quiz.stage.invite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 06.12.15.
 */
public class InviteStage extends Stage {

    private static final String TAG = "InviteStage";
    private final MyGdxGame myGdxGame;
    private final GDXFacebook gdxFacebook;

    public InviteStage(MyGdxGame myGdxGame, GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
        this.myGdxGame = myGdxGame;
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log(TAG, "Keycode" + keycode);
        if (keycode == Input.Keys.BACK) {
            myGdxGame.setScreen(new MenuScreen(myGdxGame, gdxFacebook));
            return true;
        }
        return false;
    }
}
