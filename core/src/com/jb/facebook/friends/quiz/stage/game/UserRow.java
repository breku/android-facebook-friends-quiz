package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.game.image.ImageService;

/**
 * Created by brekol on 06.12.15.
 */
public class UserRow extends Actor {

    private final BitmapFont font;

    private String username;

    private int index;
    private TextureRegion textureRegion;

    public UserRow(ImageService imageService, UserDetails userDetails, int index) {
        this.username = userDetails.getName();
        this.index = index;
        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" + ".png"), false);
        font.setColor(Color.BLACK);
        textureRegion = imageService.getImage(userDetails.getProfilePicture().getProfilePictureData().getUrl());
    }

    private static final int TOP_LINE = 1600;

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        font.draw(spriteBatch, username, 200, getRowY());

        if (textureRegion != null) {
            spriteBatch.draw(textureRegion, 100, getRowY());
        }
    }

    private int getRowY() {
        return TOP_LINE - (index * 200);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
