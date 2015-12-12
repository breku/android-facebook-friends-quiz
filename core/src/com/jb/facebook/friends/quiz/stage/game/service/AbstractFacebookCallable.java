package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.jb.facebook.friends.quiz.callback.AbstractFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by brekol on 12.12.15.
 */
public abstract class AbstractFacebookCallable<T> implements Callable<T> {

    private static final String TAG = "AbstractFacebookCallable";
    protected final CountDownLatch countDownLatch = new CountDownLatch(1);
    private final GDXFacebook gdxFacebook;
    private final String requestGraphNode;
    private final String requestGraphFields;
    protected String jsonResult;

    protected AbstractFacebookCallable(final GDXFacebook gdxFacebook, final String requestGraphNode, final String requestGraphFields) {
        this.gdxFacebook = gdxFacebook;
        this.requestGraphNode = requestGraphNode;
        this.requestGraphFields = requestGraphFields;
    }

    protected void initializeResult() throws InterruptedException {
        Gdx.app.log(this.getClass().getSimpleName(), "#initializeResult>> Service called");

        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode(requestGraphNode).putField("fields", requestGraphFields).useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new AbstractFacebookCallback() {
            @Override
            public void onSuccess(GDXFacebookGraphResult result) {
                Gdx.app.log(this.getClass().getSimpleName(), "#initializeResult result=" + result.getResultAsJson());
                jsonResult = result.getResultAsJson();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(5L, TimeUnit.SECONDS);
        Gdx.app.log(this.getClass().getSimpleName(), "#initializeResult<< Service finished");
    }
}
