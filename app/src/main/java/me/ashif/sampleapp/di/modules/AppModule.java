package me.ashif.sampleapp.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ashif.sampleapp.util.AppUtils;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class AppModule {
    /*
    complete app level dependencies should be included here
     */

    @Singleton
    @Provides
    public AppUtils providesAppUtils() {
        return new AppUtils();
    }

    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application;
    }
}
