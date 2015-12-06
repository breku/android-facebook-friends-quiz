package com.jb.facebook.friends.quiz.stage.invite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 06.12.15.
 */
public class InviteScreen extends AbstractScreen {
    private static final String TAG = "InviteScreen";
    private final MyGdxGame myGdxGame;
    private final GDXFacebook gdxFacebook;
    private final InviteStage inviteStage;

    public InviteScreen(MyGdxGame myGdxGame, GDXFacebook gdxFacebook) {
        this.myGdxGame = myGdxGame;
        this.gdxFacebook = gdxFacebook;
        inviteStage = new InviteStage(myGdxGame, gdxFacebook);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inviteStage);
    }

    @Override
    public void render(float delta) {
        inviteStage.act(delta);
        inviteStage.draw();
    }


}
