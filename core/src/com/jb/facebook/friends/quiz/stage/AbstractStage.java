package com.jb.facebook.friends.quiz.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by brekol on 06.12.15.
 */
public class AbstractStage extends Stage {

    public AbstractStage() {
        super(new StretchViewport(1080,1920));}
}
