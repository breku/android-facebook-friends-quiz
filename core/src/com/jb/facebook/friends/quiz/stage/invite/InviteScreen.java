package com.jb.facebook.friends.quiz.stage.invite;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 06.12.15.
 */
public class InviteScreen extends AbstractScreen {
    private static final String TAG = "InviteScreen";
    private final MyGdxGame myGdxGame;
    private final GDXFacebook gdxFacebook;

    public InviteScreen(MyGdxGame myGdxGame, GDXFacebook gdxFacebook) {
        super(new InviteStage(myGdxGame, gdxFacebook));


        this.myGdxGame = myGdxGame;
        this.gdxFacebook = gdxFacebook;

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public ScreenType getTargetScreenType() {
        return stage.getTargetScreenType();
    }
}
