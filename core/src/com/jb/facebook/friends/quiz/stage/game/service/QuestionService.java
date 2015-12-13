package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jb.facebook.friends.quiz.json.MusicData;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.common.font.FontManager;
import com.jb.facebook.friends.quiz.stage.game.button.Mark;
import com.jb.facebook.friends.quiz.stage.game.question.AbstractQuestion;
import com.jb.facebook.friends.quiz.stage.game.question.MusicQuestion;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by brekol on 13.12.15.
 */
public class QuestionService {

    private static final String TAG = "QuestionService";

    private final ImageService imageService;
    private final FontManager fontManager;

    @Inject
    public QuestionService(ImageService imageService, FontManager fontManager) {
        this.imageService = imageService;
        this.fontManager = fontManager;
    }

    public List<AbstractQuestion> generateQuestionList(final UserDetails targetUserDetails, final UserDetails fakeUserDetails) {
        Gdx.app.log(TAG, ">> #generateQuestionList called");
        List<AbstractQuestion> result = new ArrayList<>();
        final String targetUsername = targetUserDetails.getName();
        result.addAll(getMusicQuestions(targetUserDetails.getMusic().getMusicDataList(), targetUsername, true));
        result.addAll(getMusicQuestions(fakeUserDetails.getMusic().getMusicDataList(), targetUsername, false));
        Collections.shuffle(result);
        result = result.subList(0, result.size() > 10 ? 10 : result.size());
        Gdx.app.log(TAG, "<< #generateQuestionList finished with result=" + result);
        return result;
    }

    public Map<String, Mark> createMarks(List<AbstractQuestion> questionList) {
        final Map<String, Mark> result = new HashMap<>();
        for (int i = 0; i < questionList.size(); i++) {
            result.put(questionList.get(i).toString(), new Mark(i));
        }
        return result;
    }

    private List<AbstractQuestion> getMusicQuestions(List<MusicData> musicDataList, String targetUsername, boolean questionCorrect) {
        final List<AbstractQuestion> result = new ArrayList<>();
        for (MusicData musicData : musicDataList) {
            if (musicData.getProfilePicture().getProfilePictureData().getHeight() > 600) {
                final TextureRegion textureRegion = imageService.getImage(musicData.getProfilePicture().getProfilePictureData().getUrl());
                result.add(new MusicQuestion(fontManager, textureRegion, musicData.getName(), targetUsername, questionCorrect));
            }
        }
        return result;
    }
}
