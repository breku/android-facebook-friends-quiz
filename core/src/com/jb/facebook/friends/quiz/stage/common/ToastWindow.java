package com.jb.facebook.friends.quiz.stage.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by brekol on 06.12.15.
 */
public class ToastWindow extends Actor {
    private final BitmapFont font;
    private String text;

    public ToastWindow() {
        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" +
                ".png"), false);
        setVisible(false);
        font.setColor(Color.BLACK);
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        font.draw(spriteBatch, text, 100, 1900);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void showToast(final String text) {
        this.text = text;
        setVisible(true);

        if (isVisible()) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    setVisible(false);
                }
            }, 5f);
        }
    }
}
