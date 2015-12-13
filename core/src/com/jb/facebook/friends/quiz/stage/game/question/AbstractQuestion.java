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
    private final TextureRegion textureRegion;
    private final String questionString;
    private final boolean questionCorrect;
    private final BitmapFont font;
    private boolean clicked = false;

    private int actorX = 100;
    private int actorY = 1200;

    public AbstractQuestion(FontManager fontManager, TextureRegion textureRegion, String questionString, boolean questionCorrect) {
        this.textureRegion = textureRegion;
        this.questionString = questionString;
        this.questionCorrect = questionCorrect;
        setBounds(actorX, actorY, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

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
                Gdx.app.log(TAG, "Questions Clicked");
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

        font.draw(spriteBatch, questionString, actorX, actorY);

        if (textureRegion != null) {
            spriteBatch.draw(textureRegion, actorX, actorY);
        }
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
