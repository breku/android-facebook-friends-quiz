package com.jb.facebook.friends.quiz.stage.game.image;

import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.application.ApplicationService;
import com.jb.facebook.friends.quiz.configuration.FacebookConfig;
import com.jb.facebook.friends.quiz.login.LoginService;
import com.jb.facebook.friends.quiz.stage.game.GameScreen;
import com.jb.facebook.friends.quiz.stage.game.GameStage;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import com.jb.facebook.friends.quiz.stage.menu.MenuStage;
import dagger.Module;
import dagger.Provides;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookSystem;

import javax.inject.Singleton;

/**
 * Created by brekol on 07.12.15.
 */
@Module(
        injects = {
                MyGdxGame.class,
        }
)
public class GameModule {

    @Provides
    @Singleton
    public GDXFacebook getGdxFacebook() {
        return GDXFacebookSystem.install(new FacebookConfig());
    }

    @Provides
    public LoginService loginService(GDXFacebook gdxFacebook) {
        return new LoginService(gdxFacebook);
    }

    @Provides
    public ApplicationService applicationService(GDXFacebook gdxFacebook) {
        return new ApplicationService(gdxFacebook);
    }

    @Provides
    public MenuStage menuStage(GDXFacebook gdxFacebook) {
        return new MenuStage(gdxFacebook);
    }

    @Provides
    public GameStage gameStage(GDXFacebook gdxFacebook) {
        return new GameStage(gdxFacebook);
    }

    @Provides
    public MenuScreen menuScreen(MenuStage menuStage) {
        return new MenuScreen(menuStage);
    }

    @Provides
    public GameScreen gameScreen(GameStage gameStage) {
        return new GameScreen(gameStage);
    }
}
