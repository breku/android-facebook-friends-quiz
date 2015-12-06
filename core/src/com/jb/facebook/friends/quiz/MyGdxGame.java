package com.jb.facebook.friends.quiz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jb.facebook.friends.quiz.application.ApplicationService;
import com.jb.facebook.friends.quiz.configuration.FacebookConfig;
import com.jb.facebook.friends.quiz.login.LoginService;
import com.jb.facebook.friends.quiz.stage.AbstractScreen;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookSystem;

public class MyGdxGame extends Game {
    private static final String TAG = "MyGdxGame";
    SpriteBatch batch;

    private GDXFacebook gdxFacebook;
    private LoginService loginService;
    private ApplicationService applicationService;

    private AbstractScreen currentScreen;

    @Override
    public void create() {
        initialize();
        createServices();

        currentScreen = new MenuScreen(this, gdxFacebook);
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

        batch.end();
    }

    private void initialize() {
        batch = new SpriteBatch();
        Gdx.input.setCatchBackKey(true);

    }

    private void createServices() {
        gdxFacebook = GDXFacebookSystem.install(new FacebookConfig());
        loginService = new LoginService(gdxFacebook);
        applicationService = new ApplicationService(gdxFacebook);
    }
}
