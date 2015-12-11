package com.jb.facebook.friends.quiz.stage;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.game.GameScreen;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;

import javax.inject.Inject;

/**
 * Created by brekol on 11.12.15.
 */
public class ScreenManager {

    private static final String TAG = "ScreenManager";
    private final MenuScreen menuScreen;
    private final GameScreen gameScreen;

    @Inject
    public ScreenManager(MenuScreen menuScreen, GameScreen gameScreen) {
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;
    }

    public AbstractScreen getInitScreen() {
        return menuScreen;
    }

    public void changeScreen(ScreenType targetScreenType) {

    }

    public void handleTargetScreenType(MyGdxGame myGdxGame, AbstractScreen currentScreen) {
        if(!ScreenType.NONE.equals(currentScreen.getTargetScreenType())){
            final ScreenType targetScreenType = currentScreen.getTargetScreenType();
            Gdx.app.log(TAG, ">> Changing screen to=" + targetScreenType.name());
            disposeCurrentScreen(currentScreen);
            changeScreen(myGdxGame, targetScreenType);
        }


    }

    private void changeScreen(MyGdxGame myGdxGame, ScreenType targetScreenType) {
        if(ScreenType.GAME.equals(targetScreenType)){
            myGdxGame.setScreen(gameScreen);
        }else if(ScreenType.MENU.equals(targetScreenType)){
            myGdxGame.setScreen(menuScreen);
        }
    }

    private void disposeCurrentScreen(AbstractScreen currentScreen) {
        Gdx.app.log(TAG, ">> Disposing current screen=" + currentScreen);
        currentScreen.dispose();
        Gdx.app.log(TAG, "<< Disposing finished");
    }
}
