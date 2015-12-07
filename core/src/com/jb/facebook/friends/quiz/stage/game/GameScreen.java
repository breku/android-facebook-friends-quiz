package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 06.12.15.
 */
public class GameScreen extends AbstractScreen {
    private final MyGdxGame myGdxGame;
    private final GDXFacebook gdxFacebook;
    private final GameStage gameStage;

    public GameScreen(MyGdxGame myGdxGame, GDXFacebook gdxFacebook) {
        this.myGdxGame = myGdxGame;
        this.gdxFacebook = gdxFacebook;
        gameStage = new GameStage(myGdxGame, gdxFacebook);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        gameStage.act(delta);
        gameStage.draw();
    }
}
