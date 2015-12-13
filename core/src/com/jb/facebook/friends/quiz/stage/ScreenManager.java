package com.jb.facebook.friends.quiz.stage;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.game.GameScreen;
import com.jb.facebook.friends.quiz.stage.invite.InviteScreen;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import com.jb.facebook.friends.quiz.stage.pregame.PreGameScreen;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by brekol on 11.12.15.
 */
public class ScreenManager {

    private static final String TAG = "ScreenManager";
    private final MenuScreen menuScreen;
    private final PreGameScreen preGameScreen;

    private final InviteScreen inviteScreen;
    private final GameScreen gameScreen;

    @Inject
    public ScreenManager(MenuScreen menuScreen, PreGameScreen preGameScreen, InviteScreen inviteScreen, GameScreen gameScreen) {
        this.menuScreen = menuScreen;
        this.preGameScreen = preGameScreen;
        this.inviteScreen = inviteScreen;
        this.gameScreen = gameScreen;
    }

    public AbstractScreen getInitScreen() {
        return menuScreen;
    }

    public void handleTargetScreenType(MyGdxGame myGdxGame, AbstractScreen currentScreen) {
        if (!ScreenType.NONE.equals(currentScreen.getTargetScreenType())) {
            final ScreenType targetScreenType = currentScreen.getTargetScreenType();
            Gdx.app.log(TAG, ">> Changing screen to=" + targetScreenType.name());
            disposeCurrentScreen(currentScreen);
            final Map<String, Object> previousScreenAdditionalData = currentScreen.getAdditionalData();
            changeScreen(myGdxGame, targetScreenType, previousScreenAdditionalData);
        }
    }

    private void changeScreen(MyGdxGame myGdxGame, ScreenType targetScreenType, Map<String, Object> previousScreenAdditionalData) {
        final AbstractScreen newScreen = getTargetScreen(targetScreenType);
        newScreen.setAdditionalData(previousScreenAdditionalData);
        myGdxGame.setScreen(newScreen);
    }

    private AbstractScreen getTargetScreen(ScreenType screenType) {

        if (ScreenType.PRE_GAME.equals(screenType)) {
            return preGameScreen;
        } else if (ScreenType.MENU.equals(screenType)) {
            return menuScreen;
        } else if (ScreenType.GAME.equals(screenType)) {
            return gameScreen;
        }

        return null;
    }

    private void disposeCurrentScreen(AbstractScreen currentScreen) {
        Gdx.app.log(TAG, ">> Disposing current screen=" + currentScreen.getClass().getSimpleName());
        currentScreen.dispose();
        Gdx.app.log(TAG, "<< Disposing finished");
    }
}
