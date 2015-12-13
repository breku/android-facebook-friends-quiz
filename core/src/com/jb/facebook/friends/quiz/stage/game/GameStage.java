package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.game.button.CorrectButton;
import com.jb.facebook.friends.quiz.stage.game.button.IncorrectButton;
import com.jb.facebook.friends.quiz.stage.game.button.Mark;
import com.jb.facebook.friends.quiz.stage.game.question.AbstractQuestion;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;
import com.jb.facebook.friends.quiz.stage.game.service.QuestionService;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by brekol on 12.12.15.
 */
public class GameStage extends AbstractStage {

    private final GameService gameService;
    private final ImageService imageService;
    private final QuestionService questionService;
    private Queue<AbstractQuestion> questions;
    private AbstractQuestion currentQuestion;
    private UserDetails userDetails;
    private BackButton backButton;
    private CorrectButton correctButton;
    private IncorrectButton incorrectButton;
    private Map<String, Mark> marks = new HashMap<>();

    @Inject
    public GameStage(GameService gameService, ImageService imageService, QuestionService questionService) {
        this.gameService = gameService;
        this.imageService = imageService;
        this.questionService = questionService;
    }

    @Override
    public void disposeStage() {
        backButton.remove();
        correctButton.remove();
        incorrectButton.remove();
        for (AbstractQuestion question : questions) {
            question.remove();
        }
        for (Map.Entry<String, Mark> entry : marks.entrySet()) {
            entry.getValue().remove();
        }
    }

    @Override
    public void initialize() {
        createButtons();
        initializeQuestions();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
         if (backButton.isClicked()) {
            backButton.setClicked(false);
            returnToMenu();
        }
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

    private void initializeQuestions() {
        final String userId = (String) additionalData.get(ContextConstants.USER_ID_ADDITIONAL_DATA_KEY);
        userDetails = gameService.getUserDetails(userId);
        final UserDetails myUserDetails = gameService.getMyUserDetails();
        final List<AbstractQuestion> questionList = questionService.generateQuestionList(userDetails, myUserDetails);
        questions = new ArrayDeque<>(questionList);

        marks = questionService.createMarks(questionList);
        for (Map.Entry<String, Mark> entry : marks.entrySet()) {
            addActor(entry.getValue());
        }
        initializeNextQuestion();
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
