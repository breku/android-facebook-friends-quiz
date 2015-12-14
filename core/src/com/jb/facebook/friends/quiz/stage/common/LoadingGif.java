package com.jb.facebook.friends.quiz.stage.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by brekol on 14.12.15.
 */
public class LoadingGif extends Actor {

    private static final int FRAME_ROWS = 4;
    private static final int FRAME_COLS = 3;

    Animation animation;
    float stateTime;
    TextureRegion currentFrame;

    public LoadingGif() {
        final TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        initializeFrames(frames);
        animation = new Animation(0.05f, frames);
        stateTime = 0f;
        setVisible(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();

        currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, 400, 500);

    }

    private void initializeFrames(TextureRegion[] frames) {
        Texture texture = new Texture(Gdx.files.internal("common/download.jpg")); // #9
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }
    }
}
