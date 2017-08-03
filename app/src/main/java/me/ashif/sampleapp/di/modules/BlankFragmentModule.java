package me.ashif.sampleapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ashif.sampleapp.ui.fragments.BlankFragment;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class BlankFragmentModule {
    /*
    all the dependencies specific to the fragment must
    be listed here
     */

    @Provides
    @Singleton
    public BlankFragment providesBlankFragment(BlankFragment blankFragment){
        return blankFragment;
    }
}
