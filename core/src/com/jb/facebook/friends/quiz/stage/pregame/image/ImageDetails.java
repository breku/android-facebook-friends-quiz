package com.jb.facebook.friends.quiz.stage.pregame.image;

import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by brekol on 07.12.15.
 */
public class ImageDetails {

    final Pixmap pixmap;
    final int width;
    final int height;

    public ImageDetails(int height, int width, Pixmap pixmap) {
        this.height = height;
        this.width = width;
        this.pixmap = pixmap;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
