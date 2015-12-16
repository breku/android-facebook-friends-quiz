package com.jb.facebook.friends.quiz.stage.pregame.image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by brekol on 07.12.15.
 */
public class TextureRegionCallable implements Callable<ImageDetails> {

    private static final String TAG = "TextureRegionCallable";
    private final String url;

    public TextureRegionCallable(String url) {
        this.url = url;
    }

    @Override
    public ImageDetails call() throws Exception {
        Gdx.app.debug(TAG, ">> Downloading image from url=" + url);
        final ImageDetails result = getTextureRegion();
        Gdx.app.debug(TAG, "<< Downloading image finished");
        return result;
    }

    private ImageDetails getTextureRegion() {
        byte[] bytes = new byte[1024 * 1024]; // assuming the content is not bigger than 200kb.
        int numBytes = download(bytes, url);
        if (numBytes != 0) {
            // load the pixmap, make it a power of two if necessary (not needed for GL ES 2.0!)
            Pixmap pixmap = new Pixmap(bytes, 0, numBytes);
            final int originalWidth = pixmap.getWidth();
            final int originalHeight = pixmap.getHeight();
            int width = MathUtils.nextPowerOfTwo(pixmap.getWidth());
            int height = MathUtils.nextPowerOfTwo(pixmap.getHeight());
            final Pixmap potPixmap = new Pixmap(width, height, pixmap.getFormat());
            potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, pixmap.getWidth(), pixmap.getHeight());
            pixmap.dispose();
            return new ImageDetails(originalHeight, originalWidth, potPixmap);
//
        }
        return null;
    }

    private int download(byte[] out, String url) {
        InputStream in = null;
        try {
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setUseCaches(true);
            conn.connect();
            in = conn.getInputStream();
            int readBytes = 0;
            while (true) {
                int length = in.read(out, readBytes, out.length - readBytes);
                if (length == -1) break;
                readBytes += length;
            }
            return readBytes;
        } catch (Exception ex) {
            return 0;
        } finally {
            StreamUtils.closeQuietly(in);
        }
    }
}
