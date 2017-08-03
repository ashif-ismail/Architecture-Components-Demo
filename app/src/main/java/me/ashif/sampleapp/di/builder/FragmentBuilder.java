package me.ashif.sampleapp.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ashif.sampleapp.di.modules.BlankFragmentModule;
import me.ashif.sampleapp.ui.fragments.BlankFragment;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */
@Module
public abstract class FragmentBuilder {
    /*
    all the fragments which are to be attached to
    dagger should be listed here
     */

    @ContributesAndroidInjector
    abstract BlankFragment providesBlankFragment();
}
