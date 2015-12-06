package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jb.facebook.friends.quiz.json.UserDetails;

/**
 * Created by brekol on 06.12.15.
 */
public class UserRow extends Actor {

    private final BitmapFont font;

    private String username;

    private int index;

    public UserRow(UserDetails userDetails, int index) {
        this.username = userDetails.getName();
        this.index = index;
        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" +
                ".png"), false);
        font.setColor(Color.BLACK);
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        font.draw(spriteBatch, username, 100, 1900 - (index * 50));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
