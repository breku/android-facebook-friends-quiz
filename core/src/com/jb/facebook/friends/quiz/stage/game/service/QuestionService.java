package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jb.facebook.friends.quiz.json.PictureData;
import com.jb.facebook.friends.quiz.json.PictureObject;
import com.jb.facebook.friends.quiz.json.ProfilePictureData;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.game.button.Mark;
import com.jb.facebook.friends.quiz.stage.game.question.AbstractQuestion;
import com.jb.facebook.friends.quiz.stage.game.question.QuestionType;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by brekol on 13.12.15.
 */
public class QuestionService {

    private static final String TAG = "QuestionService";
    private static final int MINIMUM_HEIGHT = 250;
    private final ImageService imageService;
    private final QuestionFactory questionFactory;

    @Inject
    public QuestionService(ImageService imageService, QuestionFactory questionFactory) {
        this.imageService = imageService;
        this.questionFactory = questionFactory;
    }

    public List<AbstractQuestion> generateQuestionList(final UserDetails targetUserDetails, final UserDetails fakeUserDetails) {
        Gdx.app.log(TAG, ">> #generateQuestionList called");
        List<AbstractQuestion> result = new ArrayList<>();
        final String targetUsername = targetUserDetails.getName();

        result.addAll(getQuestions(targetUserDetails.getMusic(), targetUsername, true, QuestionType.MUSIC));
        result.addAll(getQuestions(fakeUserDetails.getMusic(), targetUsername, false, QuestionType.MUSIC));
        result.addAll(getQuestions(targetUserDetails.getMovies(), targetUsername, true, QuestionType.MOVIE));
        result.addAll(getQuestions(fakeUserDetails.getMovies(), targetUsername, false, QuestionType.MOVIE));
        result.addAll(getQuestions(targetUserDetails.getBooks(), targetUsername, true, QuestionType.BOOK));
        result.addAll(getQuestions(fakeUserDetails.getBooks(), targetUsername, false, QuestionType.BOOK));

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

    private List<AbstractQuestion> getQuestions(PictureObject pictureObject, String targetUsername, boolean questionCorrect,
                                                QuestionType questionType) {
        final List<AbstractQuestion> result = new ArrayList<>();
        if (pictureObject != null && pictureObject.getPictureDataList() != null) {
            for (PictureData pictureData : pictureObject.getPictureDataList()) {
                if (hasCorrectPicture(pictureData.getProfilePicture().getProfilePictureData())) {
                    final TextureRegion textureRegion = imageService.getImage(pictureData.getProfilePicture().getProfilePictureData()
                            .getUrl());
                    final AbstractQuestion question = questionFactory.createQuestion(targetUsername, questionCorrect, pictureData,
                            textureRegion, questionType);
                    result.add(question);
                }
            }
        }
        return result;
    }

    private boolean hasCorrectPicture(ProfilePictureData profilePictureData) {
        return profilePictureData.getHeight() > MINIMUM_HEIGHT && !profilePictureData.isBlank();
    }
}
