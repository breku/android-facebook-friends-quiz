package com.jb.facebook.friends.quiz.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by brekol on 05.12.15.
 */
public class PlayButton extends Actor {

    private boolean clicked;

    Texture texture = new Texture("play.png");
    float actorX = 0, actorY = 0;


    public PlayButton() {
        setBounds(actorX,actorY,texture.getWidth(),texture.getHeight());

        addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((PlayButton) event.getTarget()).clicked = true;
                Gdx.app.log(TAG, "Clicked");
                return true;
            }
        });
    }
    private static final String TAG = "PlayButton";

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,actorX,actorY);

    }



    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClicked() {
        return clicked;
    }
}
