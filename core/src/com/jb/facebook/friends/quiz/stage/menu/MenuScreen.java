package com.jb.facebook.friends.quiz.stage.menu;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

/**
 * Created by brekol on 06.12.15.
 */
public class MenuScreen extends AbstractScreen {
    private final MenuStage menuStage;


    public MenuScreen(final MyGdxGame myGdxGame, final GDXFacebook gdxFacebook) {
        menuStage = new MenuStage(myGdxGame,gdxFacebook);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);

    }

    @Override
    public void render(float delta) {
        menuStage.act(delta);
        menuStage.draw();
    }

}
