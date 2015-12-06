package com.jb.facebook.friends.quiz.stage.menu.button;

import com.badlogic.gdx.graphics.Texture;
import com.jb.facebook.friends.quiz.stage.common.AbstractButton;

/**
 * Created by brekol on 06.12.15.
 */
public abstract class AbstractMenuButton extends AbstractButton {


    public AbstractMenuButton(Texture texture, float actorX, float actorY) {
        super(texture, actorX, actorY, 0.875f,1.0f);
    }
}
