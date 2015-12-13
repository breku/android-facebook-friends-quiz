package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jb.facebook.friends.quiz.json.MusicData;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.game.question.AbstractQuestion;
import com.jb.facebook.friends.quiz.stage.game.question.MusicQuestion;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by brekol on 13.12.15.
 */
public class QuestionService {

    private static final String TAG = "QuestionService";

    private final ImageService imageService;

    @Inject
    public QuestionService(ImageService imageService) {
        this.imageService = imageService;
    }

    public List<AbstractQuestion> generateQuestionList(final UserDetails targetUserDetails, final UserDetails fakeUserDetails) {
        Gdx.app.log(TAG, ">> #generateQuestionList called");
        List<AbstractQuestion> result = new ArrayList<>();
        final String targetUsername = targetUserDetails.getName();
        result.addAll(getMusicQuestions(targetUserDetails.getMusic().getMusicDataList(), targetUsername, true));
        result.addAll(getMusicQuestions(fakeUserDetails.getMusic().getMusicDataList(), targetUsername, false));
        Collections.shuffle(result);
        Gdx.app.log(TAG, "<< #generateQuestionList finished with result=" + result);
        return result;
    }

    private List<AbstractQuestion> getMusicQuestions(List<MusicData> musicDataList, String targetUsername, boolean questionCorrect) {
        final List<AbstractQuestion> result = new ArrayList<>();
        for (MusicData musicData : musicDataList) {
            final TextureRegion textureRegion = imageService.getImage(musicData.getProfilePicture().getProfilePictureData().getUrl());
            result.add(new MusicQuestion(textureRegion, musicData.getName(),targetUsername, questionCorrect));
        }
        return result;
    }
}
