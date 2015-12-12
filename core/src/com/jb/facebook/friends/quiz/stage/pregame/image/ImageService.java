package com.jb.facebook.friends.quiz.stage.pregame.image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javax.inject.Inject;
import java.util.concurrent.*;

/**
 * Created by brekol on 07.12.15.
 */
public class ImageService {

    private static final String TAG = "ImageService";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Cache<String, TextureRegion> cache = CacheBuilder.newBuilder().maximumSize(200).expireAfterWrite(100, TimeUnit
            .MINUTES).build();

    @Inject
    public ImageService() {

    }

    public TextureRegion getImage(final String url) {

        try {
            return cache.get(url, new Callable<TextureRegion>() {
                @Override
                public TextureRegion call() throws Exception {
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
            });
        } catch (ExecutionException e) {
            Gdx.app.log(TAG, "Error during getting image from cache", e);
        }
        return null;
    }
}
