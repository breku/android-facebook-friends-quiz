package com.jb.facebook.friends.quiz.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 06.12.15.
 */
public abstract class AbstractStage extends Stage {

    private ScreenType targetScreenType = ScreenType.NONE;

    protected Map<String, Object> additionalData = new HashMap<>();

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }

    public AbstractStage() {
        super(new StretchViewport(ContextConstants.SCREEN_WIDTH, ContextConstants.SCREEN_HEIGHT));
    }

    public ScreenType getTargetScreenType() {
        return targetScreenType;
    }

    public void setTargetScreenType(ScreenType targetScreenType) {
        this.targetScreenType = targetScreenType;
    }

    public void initialize() {
        // intentionally left blank
    }
}
