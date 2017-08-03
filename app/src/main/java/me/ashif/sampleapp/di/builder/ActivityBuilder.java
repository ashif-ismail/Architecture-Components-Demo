package me.ashif.sampleapp.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ashif.sampleapp.MainActivity;

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

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract MainActivity providesMainActivity();
}
