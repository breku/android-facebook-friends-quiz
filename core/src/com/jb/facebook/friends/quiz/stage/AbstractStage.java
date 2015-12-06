package com.jb.facebook.friends.quiz.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;

/**
 * Created by brekol on 06.12.15.
 */
public class AbstractStage extends Stage {

    public AbstractStage() {
        super(new StretchViewport(ContextConstants.SCREEN_WIDTH, ContextConstants.SCREEN_HEIGHT));
    }
}
