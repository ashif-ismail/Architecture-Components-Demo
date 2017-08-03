package me.ashif.sampleapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ashif.sampleapp.MainActivity;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class MainActivityModule {
    @Provides
    @Singleton
    public MainActivity providesMainActivty(MainActivity mainActivity){
        return mainActivity;
    }
}
