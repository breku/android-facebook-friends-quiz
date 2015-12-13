package com.jb.facebook.friends.quiz.stage.pregame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

/**
 * Created by brekol on 06.12.15.
 */
public class UserRow extends Actor {

    private static final int TOP_LINE = 1600;
    private static final String TAG = "UserRow";
    private final BitmapFont font;
    private final String username;
    private final String userId;
    private final GameService gameService;
    private int index;
    private TextureRegion textureRegion;
    private boolean clicked = false;

    public UserRow(final GameService gameService, ImageService imageService, UserDetails userDetails, int index) {
        this.gameService = gameService;
        this.username = userDetails.getName();
        this.userId = userDetails.getId();
        this.index = index;
        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" + ".png"), false);
        font.setColor(Color.BLACK);
        textureRegion = imageService.getImage(userDetails.getProfilePicture().getProfilePictureData().getUrl());
        setBounds(100, getRowY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        setDebug(true);
        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((UserRow) event.getTarget()).clicked = true;
                Gdx.app.log(TAG, "UserRow Clicked");
            }
        });
    }

    public String getUserId() {
        return userId;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        font.draw(spriteBatch, username, 200, getRowY());

        if (textureRegion != null) {
            spriteBatch.draw(textureRegion, 100, getRowY());
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private int getRowY() {
        return TOP_LINE - (index * 200);
    }
}
