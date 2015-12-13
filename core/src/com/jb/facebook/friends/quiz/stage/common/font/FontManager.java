package com.jb.facebook.friends.quiz.stage.common.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import javax.inject.Inject;

/**
 * Created by brekol on 13.12.15.
 */
public class FontManager {

    @Inject
    public FontManager() {
    }

    public BitmapFont getDefaultFont(){
        final BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/comic44.fnt"), Gdx.files.internal("fonts/comic44.png"), false);
        font.setColor(Color.BLACK);
        return font;
    }
}
