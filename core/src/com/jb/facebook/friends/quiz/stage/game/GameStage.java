package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Input;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.common.LoadingGif;
import com.jb.facebook.friends.quiz.stage.game.button.CorrectButton;
import com.jb.facebook.friends.quiz.stage.game.button.IncorrectButton;
import com.jb.facebook.friends.quiz.stage.game.button.Mark;
import com.jb.facebook.friends.quiz.stage.game.question.AbstractQuestion;
import com.jb.facebook.friends.quiz.stage.game.service.QuestionService;
import com.jb.facebook.friends.quiz.stage.game.service.UserDetailsRequest;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by brekol on 12.12.15.
 */
public class GameStage extends AbstractStage {

    private static final String ID_ME_KEY = "me";
    private final GDXFacebook gdxFacebook;
    private final ImageService imageService;
    private final QuestionService questionService;
    private Queue<AbstractQuestion> questions;
    private AbstractQuestion currentQuestion;
    private BackButton backButton;
    private CorrectButton correctButton;
    private IncorrectButton incorrectButton;
    private Map<String, Mark> marks = new HashMap<>();
    private LoadingGif loadingGif;
    private Cache<String, UserDetails> userDetailsCache = CacheBuilder.newBuilder().build();
    private String userId;

    @Inject
    public GameStage(GDXFacebook gdxFacebook, ImageService imageService, QuestionService questionService) {
        this.gdxFacebook = gdxFacebook;
        this.imageService = imageService;
        this.questionService = questionService;
    }

    @Override
    public void disposeStage() {
        loadingGif.remove();
        backButton.remove();
        correctButton.remove();
        incorrectButton.remove();
        for (AbstractQuestion question : questions) {
            question.remove();
        }
        questions.clear();
        for (Map.Entry<String, Mark> entry : marks.entrySet()) {
            entry.getValue().remove();
        }
        marks.clear();
        this.clear();
    }

    @Override
    public void initialize() {
        createButtons();
        initializeLoading();

        questions = new ArrayDeque<>();

        userId = (String) additionalData.get(ContextConstants.USER_ID_ADDITIONAL_DATA_KEY);

        final UserDetails myUserDetails = userDetailsCache.getIfPresent(ID_ME_KEY);
        final UserDetails userDetails = userDetailsCache.getIfPresent(userId);
        if (myUserDetails == null) {
            makeUserDetailsRequest(ID_ME_KEY);
        }
        if (userDetails == null) {
            makeUserDetailsRequest(userId);
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        handleMenuButtons();
        handleLoadingGif();
        handleInitializingQuestions();

        if (correctButton.isClicked()) {
            correctButton.setClicked(false);
            final Mark mark = marks.get(currentQuestion.toString());
            mark.setQuestionAnswered(true);
            if (currentQuestion.isQuestionCorrect()) {
                mark.setQuestionCorrect(true);
            }
            initializeNextQuestion();
        }

        if (incorrectButton.isClicked()) {
            incorrectButton.setClicked(false);
            final Mark mark = marks.get(currentQuestion.toString());
            mark.setQuestionAnswered(true);
            if (!currentQuestion.isQuestionCorrect()) {
                mark.setQuestionCorrect(true);
            }
            initializeNextQuestion();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            returnToMenu();
            return true;
        }
        return false;
    }

    private void makeUserDetailsRequest(final String userId) {
        new UserDetailsRequest(gdxFacebook, userId) {

            @Override
            public void onFacebookResponseSuccess(UserDetails result) {
                userDetailsCache.put(userId, result);
            }
        };
    }

    private void initializeLoading() {
        loadingGif = new LoadingGif();
        addActor(loadingGif);
    }

    private void handleLoadingGif() {

        if (CollectionUtils.isNotEmpty(questions) && loadingGif.isVisible()) {
            loadingGif.setVisible(false);
        }
    }

    private void handleMenuButtons() {
        if (backButton.isClicked()) {
            backButton.setClicked(false);
            returnToMenu();
        }
    }

    private void handleInitializingQuestions() {
        final UserDetails myUserDetails = userDetailsCache.getIfPresent(ID_ME_KEY);
        final UserDetails userDetails = userDetailsCache.getIfPresent(userId);
        if (userDetails != null && myUserDetails != null && CollectionUtils.isEmpty(questions)) {
            final List<AbstractQuestion> questionList = questionService.generateQuestionList(userDetails, myUserDetails);
            questions.addAll(questionList);
            marks = questionService.createMarks(questionList);
            for (Map.Entry<String, Mark> entry : marks.entrySet()) {
                addActor(entry.getValue());
            }
            initializeNextQuestion();
        }
    }

    private void initializeNextQuestion() {
        if (currentQuestion != null) {
            currentQuestion.remove();
        }
        if (!questions.isEmpty()) {
            currentQuestion = questions.remove();
            addActor(currentQuestion);
        }
    }

    private void returnToMenu() {
        setTargetScreenType(ScreenType.MENU);
    }

    private void createButtons() {
        backButton = new BackButton();
        correctButton = new CorrectButton();
        incorrectButton = new IncorrectButton();

        addActor(backButton);
        addActor(correctButton);
        addActor(incorrectButton);
    }
}
