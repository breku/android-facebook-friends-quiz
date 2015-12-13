package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jb.facebook.friends.quiz.json.PictureData;
import com.jb.facebook.friends.quiz.stage.common.font.FontManager;
import com.jb.facebook.friends.quiz.stage.game.question.*;

import javax.inject.Inject;

/**
 * Created by brekol on 13.12.15.
 */
public class QuestionFactory {

    private final FontManager fontManager;

    @Inject
    public QuestionFactory(FontManager fontManager) {
        this.fontManager = fontManager;
    }

    public AbstractQuestion createQuestion(String targetUsername, boolean questionCorrect, PictureData pictureData, TextureRegion
            textureRegion, QuestionType questionType) {
        if(QuestionType.MOVIE.equals(questionType)){
            return new MovieQuestion(fontManager, textureRegion, pictureData.getName(), targetUsername, questionCorrect);
        }else if(QuestionType.BOOK.equals(questionType)){
            return new BookQuestion(fontManager, textureRegion, pictureData.getName(), targetUsername, questionCorrect);
        }else if(QuestionType.MUSIC.equals(questionType)){
            return new MusicQuestion(fontManager, textureRegion, pictureData.getName(), targetUsername, questionCorrect);
        }else {
            throw new UnsupportedOperationException("Unsupported questionType");
        }
    }

}
