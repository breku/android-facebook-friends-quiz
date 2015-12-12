package com.jb.facebook.friends.quiz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.dagger.DaggerAdapter;
import com.jb.facebook.friends.quiz.dagger.AppModule;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = ContextConstants.SCREEN_WIDTH /2;
//		config.height = ContextConstants.SCREEN_HEIGHT /2;
		DaggerAdapter daggerAdapter = new DaggerAdapter(MyGdxGame.class, AppModule.class);


		new LwjglApplication(daggerAdapter, config);
	}
}

