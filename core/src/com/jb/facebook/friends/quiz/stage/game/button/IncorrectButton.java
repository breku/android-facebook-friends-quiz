package com.jb.facebook.friends.quiz.stage.game.button;

import com.badlogic.gdx.graphics.Texture;
import com.jb.facebook.friends.quiz.stage.common.AbstractButton;

/**
 * Created by brekol on 13.12.15.
 */
public class IncorrectButton extends AbstractButton {

    public IncorrectButton() {
        super(new Texture("game/wrong.png"), 0, 100, 0.5f, 0.6f);
    }
}
