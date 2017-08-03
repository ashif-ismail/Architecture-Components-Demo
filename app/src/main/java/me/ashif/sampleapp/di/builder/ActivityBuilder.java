package me.ashif.sampleapp.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ashif.sampleapp.MainActivity;
import me.ashif.sampleapp.di.modules.MainActivityModule;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public abstract class ActivityBuilder {

    /*
    all activities that are to be attached to
    dagger should register here.
     */

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity providesMainActivity();
}
