package com.jb.facebook.friends.quiz.stage.invite.model;

import com.badlogic.gdx.graphics.Texture;
import com.jb.facebook.friends.quiz.stage.common.AbstractButton;

/**
 * Created by brekol on 06.12.15.
 */
public class RefreshButton extends AbstractButton {

    private static final float SCALE = 0.25f;
    private static final float TARGET_SCALE = 0.4f;
    public RefreshButton() {
        super(new Texture("invite/refresh.png"), 750, 1550, SCALE,TARGET_SCALE);
    }
}
