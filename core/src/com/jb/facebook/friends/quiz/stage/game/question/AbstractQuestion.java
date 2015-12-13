package com.jb.facebook.friends.quiz.stage.game.question;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.jb.facebook.friends.quiz.stage.common.font.FontManager;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by brekol on 13.12.15.
 */
public class AbstractQuestion extends Actor {

    private static final String TAG = "Question";
    private static final int TARGET_HEIGHT = 800;
    private static final int TARGET_WIDTH = 800;
    private final TextureRegion textureRegion;
    private final String questionString;
    private final boolean questionCorrect;
    private final BitmapFont font;
    private boolean clicked = false;

    public AbstractQuestion(FontManager fontManager, TextureRegion textureRegion, String questionString, boolean questionCorrect) {
        this.textureRegion = textureRegion;
        this.questionString = questionString;
        this.questionCorrect = questionCorrect;
        setX(150);
        setY(800);

        setBounds(getX(), getY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        setScaleY((float) TARGET_HEIGHT / (float) textureRegion.getRegionHeight());
        setScaleX((float) TARGET_WIDTH / (float) textureRegion.getRegionWidth());
        font = fontManager.getDefaultFont();

        setDebug(true);
        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((AbstractQuestion) event.getTarget()).clicked = true;
                Gdx.app.log(TAG, "Question Clicked");
            }
        });
    }

    public boolean isQuestionCorrect() {
        return questionCorrect;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {

        font.draw(spriteBatch, questionString, getX() - 100, getY() - 50);

        spriteBatch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("questionCorrect", questionCorrect)
                .append("questionString", questionString)
                .toString();
    }
}
