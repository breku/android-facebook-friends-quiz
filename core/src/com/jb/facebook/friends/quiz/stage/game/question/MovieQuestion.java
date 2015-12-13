package com.jb.facebook.friends.quiz.stage.game.question;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jb.facebook.friends.quiz.stage.common.font.FontManager;

/**
 * Created by brekol on 13.12.15.
 */
public class MovieQuestion extends AbstractQuestion {

    private static final String QUESTION_FORMAT = "Does %s watched a movie %s?";

    public MovieQuestion(final FontManager fontManager, final TextureRegion textureRegion, final String artistName, String
            targetUsername, boolean questionCorrect) {
        super(fontManager, textureRegion, String.format(QUESTION_FORMAT, targetUsername, artistName), questionCorrect);
    }
}
