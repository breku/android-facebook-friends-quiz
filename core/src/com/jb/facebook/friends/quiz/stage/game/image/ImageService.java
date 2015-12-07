package com.jb.facebook.friends.quiz.stage.game.image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by brekol on 07.12.15.
 */
public class ImageService {

    private static final String TAG = "ImageService";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TextureRegion getImage(String url) {
        final Future<ImageDetails> submit = executor.submit(new TextureRegionCallable(url));
        try {
            final ImageDetails imageDetails = submit.get();
            return new TextureRegion(new Texture(imageDetails.getPixmap()), 0, 0, imageDetails.getWidth(), imageDetails.getHeight());
        } catch (InterruptedException e) {
            Gdx.app.log(TAG, "Error during downloading image", e);
        } catch (ExecutionException e) {
            Gdx.app.log(TAG, "Error during downloading image", e);
        }
        return null;
    }
}
