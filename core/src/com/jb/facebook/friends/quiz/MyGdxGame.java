package com.jb.facebook.friends.quiz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jb.facebook.friends.quiz.application.ApplicationService;
import com.jb.facebook.friends.quiz.login.LoginService;
import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import com.jb.facebook.friends.quiz.stage.ScreenManager;

import javax.inject.Inject;

public class MyGdxGame extends Game {
    private static final String TAG = "MyGdxGame";
    private final LoginService loginService;
    private final ApplicationService applicationService;
    private final ScreenManager screenManager;

    SpriteBatch batch;
    private AbstractScreen currentScreen;

    @Inject
    public MyGdxGame(LoginService loginService, ApplicationService applicationService, ScreenManager screenManager) {
        this.loginService = loginService;
        this.applicationService = applicationService;
        this.screenManager = screenManager;
    }

    @Override
    public void create() {
        initialize();

        currentScreen = screenManager.getInitScreen();
        setScreen(currentScreen);

        loginService.loginToFacebook();
        applicationService.updateApplicationAccessToken();
    }

    public void setScreen(final AbstractScreen screen) {
        super.setScreen(screen);
        currentScreen = screen;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        currentScreen.render(Gdx.graphics.getDeltaTime());
        screenManager.handleTargetScreenType(this, currentScreen);

        batch.end();
    }

    private void initialize() {
        batch = new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
    }
}
