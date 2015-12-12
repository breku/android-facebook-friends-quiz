package com.jb.facebook.friends.quiz.dagger;

import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.application.ApplicationService;
import com.jb.facebook.friends.quiz.configuration.FacebookConfig;
import com.jb.facebook.friends.quiz.login.LoginService;
import com.jb.facebook.friends.quiz.stage.game.GameScreen;
import com.jb.facebook.friends.quiz.stage.game.GameService;
import com.jb.facebook.friends.quiz.stage.game.GameStage;
import com.jb.facebook.friends.quiz.stage.game.image.ImageService;
import com.jb.facebook.friends.quiz.stage.invite.InviteScreen;
import com.jb.facebook.friends.quiz.stage.invite.InviteStage;
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
public class AppModule {

    // --------- SCREENS ----------------

    @Provides
    public MenuScreen menuScreen(MenuStage menuStage) {
        return new MenuScreen(menuStage);
    }

    @Provides
    public InviteScreen inviteScreen(InviteStage inviteStage) {
        return new InviteScreen(inviteStage);
    }

    @Provides
    public GameScreen gameScreen(GameStage gameStage) {
        return new GameScreen(gameStage);
    }

    // --------- STAGES ----------------

    @Provides
    public MenuStage menuStage(GDXFacebook gdxFacebook) {
        return new MenuStage(gdxFacebook);
    }

    @Provides
    public InviteStage inviteStage(GDXFacebook gdxFacebook) {
        return new InviteStage(gdxFacebook);
    }

    @Provides
    public GameStage gameStage(GDXFacebook gdxFacebook, GameService gameService, ImageService imageService) {
        return new GameStage(gdxFacebook, gameService, imageService);
    }


    // --------- SERVICES ----------------

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
    public GameService gameService(GDXFacebook gdxFacebook) {
        return new GameService(gdxFacebook);
    }
}
