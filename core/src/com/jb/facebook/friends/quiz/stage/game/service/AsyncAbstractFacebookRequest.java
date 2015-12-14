package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.callback.AbstractFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

/**
 * Created by brekol on 14.12.15.
 */
public abstract class AsyncAbstractFacebookRequest<T> {

    private static final String TAG = "AbstractFacebookCallable";
    private final Class<T> type;
    private final GDXFacebook gdxFacebook;
    private final String requestGraphNode;
    private final String requestGraphFields;
    protected String jsonResult;

    protected AsyncAbstractFacebookRequest(final Class<T> type, final GDXFacebook gdxFacebook, final String requestGraphNode, final
    String requestGraphFields) {
        this.type = type;
        this.gdxFacebook = gdxFacebook;
        this.requestGraphNode = requestGraphNode;
        this.requestGraphFields = requestGraphFields;
    }

    public abstract void onFacebookResponseSuccess(T result);

    protected void initializeResultAsync() {
        Gdx.app.log(this.getClass().getSimpleName(), "#initializeResultAsync>> Service called");

        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode(requestGraphNode).putField("fields", requestGraphFields).putField("limit", "100").useCurrentAccessToken();

        gdxFacebook.newGraphRequest(request, new AbstractFacebookCallback() {
            @Override
            public void onSuccess(GDXFacebookGraphResult result) {
                Gdx.app.log(AsyncAbstractFacebookRequest.this.getClass().getSimpleName(), "#initializeResultAsync asynchronousResult=" +
                        result.getResultAsJson());
                jsonResult = result.getResultAsJson();
                final T t = new Gson().fromJson(jsonResult, type);
                onFacebookResponseSuccess(t);
            }
        });
        Gdx.app.log(this.getClass().getSimpleName(), "#initializeResultAsync<< Service finished");
    }
}
