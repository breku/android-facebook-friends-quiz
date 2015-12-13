package com.jb.facebook.friends.quiz.stage.game;

import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.game.button.CorrectButton;
import com.jb.facebook.friends.quiz.stage.game.button.IncorrectButton;
import com.jb.facebook.friends.quiz.stage.game.question.AbstractQuestion;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;
import com.jb.facebook.friends.quiz.stage.game.service.QuestionService;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;

import javax.inject.Inject;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

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

    @Inject
    public GameStage(GameService gameService, ImageService imageService, QuestionService questionService) {
        this.gameService = gameService;
        this.imageService = imageService;
        this.questionService = questionService;
    }

    @Override
    public void initialize() {
        createButtons();
        final String userId = (String) additionalData.get(ContextConstants.USER_ID_ADDITIONAL_DATA_KEY);
        userDetails = gameService.getUserDetails(userId);
        final UserDetails myUserDetails = gameService.getMyUserDetails();

        final List<AbstractQuestion> questionList = questionService.generateQuestionList(userDetails, myUserDetails);
        questions = new ArrayDeque<>(questionList);

        currentQuestion = questions.remove();
        addActor(currentQuestion);
//        AbstractQuestion question = new AbstractQuestion(imageService,userDetails);
//        addActor(question);

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
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            returnToMenu();
            return true;
        }
        return false;
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
