package com.jb.facebook.friends.quiz.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.util.Map;

/**
 * Created by brekol on 06.12.15.
 */
public abstract class AbstractScreen implements Screen {

    protected final AbstractStage stage;

    protected AbstractScreen(AbstractStage stage) {
        this.stage = stage;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.initialize();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.setTargetScreenType(ScreenType.NONE);
    }

    public ScreenType getTargetScreenType() {
        return stage.getTargetScreenType();
    }

    public Map<String, Object> getAdditionalData() {
        return stage.getAdditionalData();
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.stage.setAdditionalData(additionalData);
    }
}
