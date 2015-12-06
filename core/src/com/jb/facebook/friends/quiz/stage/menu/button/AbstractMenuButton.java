package com.jb.facebook.friends.quiz.stage.menu.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by brekol on 06.12.15.
 */
public abstract class AbstractMenuButton extends Actor {

    private static final String TAG = "MenuButton";
    private static final float SCALE = 0.875f;
    private final TextureRegion textureRegion;
    private final float actorX;
    private final float actorY;
    private boolean clicked;

    public AbstractMenuButton(Texture texture, float actorX, float actorY) {
        this.actorX = actorX;
        this.actorY = actorY;
        textureRegion = new TextureRegion(texture);
        setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((AbstractMenuButton) event.getTarget()).clicked = true;
                Gdx.app.log(TAG, "Clicked");
            }
        });
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, actorX, actorY, getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), SCALE,
                SCALE, getRotation());
    }
}
