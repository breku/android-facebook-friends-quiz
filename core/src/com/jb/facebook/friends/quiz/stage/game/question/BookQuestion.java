package com.jb.facebook.friends.quiz.stage.game.question;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jb.facebook.friends.quiz.stage.common.font.FontManager;

/**
 * Created by brekol on 13.12.15.
 */
public class BookQuestion extends AbstractQuestion {
    private static final String QUESTION_FORMAT = "Did %s read a book %s?";

    public BookQuestion(final FontManager fontManager, final TextureRegion textureRegion, final String artistName, String targetUsername,
                        boolean questionCorrect) {
        super(fontManager, textureRegion, String.format(QUESTION_FORMAT, targetUsername, artistName), questionCorrect);
    }
}
