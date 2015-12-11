package com.jb.facebook.friends.quiz.stage.game.image;

import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.stage.game.GameStage;
import dagger.Module;

/**
 * Created by brekol on 07.12.15.
 */
@Module(
        injects = {
                MyGdxGame.class,
                GameStage.class
        }
)
public class ImageModule {
}
