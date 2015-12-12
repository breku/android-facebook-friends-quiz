package com.jb.facebook.friends.quiz.dagger;

import com.jb.facebook.friends.quiz.MyGdxGame;
import com.jb.facebook.friends.quiz.configuration.FacebookConfig;
import dagger.Module;
import dagger.Provides;
import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookSystem;

import javax.inject.Singleton;

/**
 * Created by brekol on 07.12.15.
 */
@Module(
        injects = {
                MyGdxGame.class,
        }

)
public class AppModule {

    @Provides
    @Singleton
    public GDXFacebook getGdxFacebook() {
        return GDXFacebookSystem.install(new FacebookConfig());
    }
}
