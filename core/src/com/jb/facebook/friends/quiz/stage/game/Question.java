package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

/**
 * Created by brekol on 13.12.15.
 */
public class Question extends Actor {

    private static final String TAG = "Question";
    private final ImageService imageService;
    private final UserDetails userDetails;
    private final TextureRegion textureRegion;
    private final BitmapFont font;
    private boolean clicked = false;

    private int actorX = 100;
    private int actorY = 500;

    public Question(ImageService imageService, UserDetails userDetails) {
        this.imageService = imageService;
        this.userDetails = userDetails;
        textureRegion = imageService.getImage(userDetails.getMusic().getMusicDataList().get(0).getProfilePicture().getProfilePictureData
                ().getUrl());
        setBounds(actorX, actorY, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        font = new BitmapFont(Gdx.files.internal("fonts/comicSans44.fnt"), Gdx.files.internal("fonts/comicSans44" + ".png"), false);
        font.setColor(Color.BLACK);

        setDebug(true);
        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((Question) event.getTarget()).clicked = true;
                Gdx.app.log(TAG, "UserRow Clicked");
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
    public void draw(Batch spriteBatch, float parentAlpha) {

        font.draw(spriteBatch, "asdf", actorX, actorY);

        if (textureRegion != null) {
            spriteBatch.draw(textureRegion, actorX, actorY);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
