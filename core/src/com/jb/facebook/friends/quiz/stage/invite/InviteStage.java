package com.jb.facebook.friends.quiz.stage.invite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.invite.model.RefreshButton;
import com.jb.facebook.friends.quiz.stage.menu.MenuScreen;
import de.tomgrill.gdxfacebook.core.GDXFacebook;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by brekol on 06.12.15.
 */
public class InviteStage extends AbstractStage {

    private static final String TAG = "InviteStage";
    private final GDXFacebook gdxFacebook;
    private RefreshButton refreshButton;
    private BackButton backButton;
    private InviteService inviteService;

    @Inject
    public InviteStage(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
        createButtons();
        initializeServices();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (refreshButton.isClicked()) {
            refreshButton.setClicked(false);
            inviteService.getUsersToInvite();
        }

        if (backButton.isClicked()) {
            backButton.setClicked(false);
            returnToMenu();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log(TAG, "Keycode" + keycode);
        if (keycode == Input.Keys.BACK) {
            returnToMenu();
            return true;
        }
        return false;
    }

    @Override
    public void disposeStage() {

    }

    private void returnToMenu() {
        setTargetScreenType(ScreenType.MENU);
    }

    private void initializeServices() {
        inviteService = new InviteService(gdxFacebook);
    }

    private void createButtons() {
        refreshButton = new RefreshButton();
        backButton = new BackButton();

        addActor(backButton);
        addActor(refreshButton);
    }
}
