package com.jb.facebook.friends.quiz.menu.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by brekol on 06.12.15.
 */
public class MenuButton extends Actor {

    private static final String TAG = "MenuButton";
    private final Texture texture;
    private final float actorX;
    private final float actorY;
    private boolean clicked;

    public MenuButton(Texture texture, float actorX, float actorY) {
        this.texture = texture;
        this.actorX = actorX;
        this.actorY = actorY;
        setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((MenuButton) event.getTarget()).clicked = true;
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
        batch.draw(texture, actorX, actorY);
    }
}
