package com.jb.facebook.friends.quiz.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.facebook.FacebookSdk;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.dagger.DaggerAdapter;
import com.jb.facebook.friends.quiz.dagger.AppModule;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		FacebookSdk.sdkInitialize(this);

		DaggerAdapter daggerAdapter = new DaggerAdapter(MyGdxGame.class, new AppModule());
		initialize(daggerAdapter, config);
//		initialize(new MyGdxGame(loginService, applicationService, screenManager), config);
	}
}
