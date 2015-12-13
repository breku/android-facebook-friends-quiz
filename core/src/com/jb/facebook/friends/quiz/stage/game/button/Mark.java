package com.jb.facebook.friends.quiz.stage.game.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by brekol on 13.12.15.
 */
public class Mark extends Actor {

    private final TextureRegion markOk;
    private final TextureRegion markNotOk;

    private boolean questionCorrect;

    private boolean questionAnswered;

    public Mark(int index) {
        markOk = new TextureRegion(new Texture("game/ok.png"));
        markNotOk = new TextureRegion(new Texture("game/notok.png"));
        setX(-200 + (index * 100));
        setY(1400);
        setBounds(getX(), getY(), markOk.getRegionWidth(), markOk.getRegionHeight());
        setScale(0.1f);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setDebug(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (questionAnswered) {
            if (questionCorrect) {
                batch.draw(markOk, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
                        getRotation());
            } else {
                batch.draw(markNotOk, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
                        getRotation());
            }
        }
    }

    public void setQuestionCorrect(boolean questionCorrect) {
        this.questionCorrect = questionCorrect;
    }

    public void setQuestionAnswered(boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }
}
