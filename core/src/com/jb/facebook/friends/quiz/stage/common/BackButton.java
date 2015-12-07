package com.jb.facebook.friends.quiz.stage.common;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by brekol on 06.12.15.
 */
public class BackButton extends AbstractButton {
    private static final float SCALE = 0.25f;
    private static final float TARGET_SCALE = 0.4f;

    public BackButton() {
        super(new Texture("common/backArrow.png"), -150, 1550, SCALE, TARGET_SCALE);
    }
}
