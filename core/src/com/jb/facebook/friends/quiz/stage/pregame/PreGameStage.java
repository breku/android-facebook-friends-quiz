package com.jb.facebook.friends.quiz.stage.pregame;

import com.badlogic.gdx.Input;
import com.jb.facebook.friends.quiz.json.UserDetails;
import com.jb.facebook.friends.quiz.json.UsersJson;
import com.jb.facebook.friends.quiz.stage.AbstractStage;
import com.jb.facebook.friends.quiz.stage.ScreenType;
import com.jb.facebook.friends.quiz.stage.common.BackButton;
import com.jb.facebook.friends.quiz.stage.common.LoadingGif;
import com.jb.facebook.friends.quiz.stage.common.font.FontManager;
import com.jb.facebook.friends.quiz.stage.game.service.GameService;
import com.jb.facebook.friends.quiz.stage.game.service.GetFriendsRequest;
import com.jb.facebook.friends.quiz.stage.game.service.UserDetailsRequest;
import com.jb.facebook.friends.quiz.stage.invite.model.RefreshButton;
import com.jb.facebook.friends.quiz.stage.pregame.image.ImageService;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.jb.facebook.friends.quiz.configuration.ContextConstants.USER_ID_ADDITIONAL_DATA_KEY;

/**
 * Created by brekol on 06.12.15.
 */
public class PreGameStage extends AbstractStage {

    private final GDXFacebook gdxFacebook;
    private final GameService gameService;
    private final ImageService imageService;
    private final FontManager fontManager;
    private RefreshButton refreshButton;
    private BackButton backButton;
    private List<UserRow> userRows;
    private LoadingGif loadingGif;
    private List<UserDetails> asyncUserList;

    @Inject
    public PreGameStage(final GDXFacebook gdxFacebook, final GameService gameService, final ImageService imageService, final FontManager
            fontManager) {
        this.gdxFacebook = gdxFacebook;
        this.gameService = gameService;
        this.imageService = imageService;
        this.fontManager = fontManager;
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
        handleInitializingFriendsList();

        for (UserRow userRow : userRows) {
            if (userRow.isClicked()) {
                userRow.setClicked(false);
                loadingGif.setVisible(false);
                additionalData.put(USER_ID_ADDITIONAL_DATA_KEY, userRow.getUserId());
                setTargetScreenType(ScreenType.GAME);
            }
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

    @Override
    public void disposeStage() {
        backButton.remove();
        refreshButton.remove();
        loadingGif.remove();
        for (final UserRow userRow : userRows) {
            userRow.remove();
        }
        userRows.clear();
        this.clear();
    }

    @Override
    public void initialize() {
        createButtons();
        initializeUsers();
        initializeLoading();
        userRows = new ArrayList<>();
    }

    private void handleInitializingFriendsList() {
        if (asyncUserList != null && CollectionUtils.isEmpty(userRows)) {
            for (int i = 0; i < asyncUserList.size(); i++) {

                userRows.add(new UserRow(fontManager, imageService, asyncUserList.get(i), i));
            }
            for (UserRow userRow : userRows) {
                addActor(userRow);
            }
        }
    }

    private void handleMenuButtons() {
        if (refreshButton.isClicked()) {
            refreshButton.setClicked(false);
        }
        if (backButton.isClicked()) {
            backButton.setClicked(false);
            returnToMenu();
        }
    }

    private void handleLoadingGif() {
        if (!userRows.isEmpty() && loadingGif.isVisible()) {
            loadingGif.setVisible(false);
        }
    }

    private void initializeLoading() {
        loadingGif = new LoadingGif();
        addActor(loadingGif);
    }

    private void initializeUsers() {

        new GetFriendsRequest(gdxFacebook) {
            @Override
            public void onFacebookResponseSuccess(UsersJson result) {
                asyncUserList = result.getUserDetailsList();
            }
        };
    }

    private void returnToMenu() {
        setTargetScreenType(ScreenType.MENU);
    }

    private void createButtons() {
        refreshButton = new RefreshButton();
        backButton = new BackButton();

        addActor(backButton);
        addActor(refreshButton);
    }
}
