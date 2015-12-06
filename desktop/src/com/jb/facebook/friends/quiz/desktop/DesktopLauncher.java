package com.jb.facebook.friends.quiz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.configuration.ContextConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = ContextConstants.SCREEN_WIDTH /2;
//		config.height = ContextConstants.SCREEN_HEIGHT /2;
		new LwjglApplication(new MyGdxGame(), config);
	}
}

