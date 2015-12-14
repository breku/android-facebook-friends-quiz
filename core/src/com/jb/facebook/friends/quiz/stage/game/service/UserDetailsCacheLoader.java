package com.jb.facebook.friends.quiz.stage.game.service;

import com.badlogic.gdx.Gdx;
import com.google.common.base.Joiner;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.*;
import com.google.gson.Gson;
import com.jb.facebook.friends.quiz.callback.AbstractFacebookCallback;
import com.jb.facebook.friends.quiz.json.UserDetails;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphRequest;
import de.tomgrill.gdxfacebook.core.GDXFacebookGraphResult;

import java.util.concurrent.*;

/**
 * Created by brekol on 14.12.15.
 */
public class UserDetailsCacheLoader extends CacheLoader<String, UserDetails> {

    private static final String NODE_NAME = "name,id";
    private static final String NODE_SEPARATOR = ",";

    private static final String COMMON_PICTURE_PROPERTIES = "{picture.height(600).width(600){height,width,is_silhouette,url},name}";
    private static final String NODE_MUSIC = "music" + COMMON_PICTURE_PROPERTIES;
    private static final String NODE_VIDEO = "movies" + COMMON_PICTURE_PROPERTIES;
    private static final String NODE_BOOKS = "books" + COMMON_PICTURE_PROPERTIES;
    private static final String NODE_LIKES = "likes" + COMMON_PICTURE_PROPERTIES;


    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("MyCacheRefresher-pool-%d").setDaemon(true).build();
    ExecutorService parentExecutor = Executors.newSingleThreadExecutor(threadFactory);
    final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(parentExecutor);
    private GDXFacebook gdxFacebook;

    public UserDetailsCacheLoader(GDXFacebook gdxFacebook) {
        this.gdxFacebook = gdxFacebook;
    }

    @Override
    public UserDetails load(String key) throws Exception {
        Gdx.app.log(this.getClass().getSimpleName(), "#initializeResultSynchronously>> Service called");

        final GDXFacebookGraphRequest request = new GDXFacebookGraphRequest()
                .setNode(key).putField("fields", Joiner.on(NODE_SEPARATOR).join(NODE_NAME, NODE_MUSIC, NODE_VIDEO, NODE_BOOKS,NODE_LIKES)).putField("limit", "100").useCurrentAccessToken();

        final UserDetails[] result = new UserDetails[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        gdxFacebook.newGraphRequest(request, new AbstractFacebookCallback() {
            @Override
            public void onSuccess(GDXFacebookGraphResult facebookGraphResult) {
                Gdx.app.log("AAA", "#initializeResultSynchronously asynchronousResult=" + facebookGraphResult.getResultAsJson());
                String jsonResult = facebookGraphResult.getResultAsJson();
                result[0] = new Gson().fromJson(jsonResult, UserDetails.class);

                countDownLatch.countDown();
            }
        });
        countDownLatch.await(5L, TimeUnit.SECONDS);
        Gdx.app.log(this.getClass().getSimpleName(), "#initializeResultSynchronously<< Service finished");
        return result[0];
    }

    @Override
    public ListenableFuture<UserDetails> reload(final String key, UserDetails oldValue) throws Exception {
        ListenableFutureTask<UserDetails> task =
                ListenableFutureTask.create(
                        new Callable<UserDetails>() {
                            public UserDetails call() throws Exception {
                                return load(key);
                            }
                        });
        executorService.execute(task);
        return task;
    }
}
