package me.ashif.sampleapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ashif.sampleapp.view.adapter.ContentAdapter;
import me.ashif.sampleapp.view.ui.home.HomeFragment;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class HomeFragmentModule {
    /*
    all the dependencies specific to the fragment must
    be listed here,optional you can skip this class creation
    if you dont have any dependencies specific to this fragment
     */

    @Provides
    @Singleton
    public HomeFragment providesBlankFragment(HomeFragment homeFragment){
        return homeFragment;
    }

    @Provides
    public ContentAdapter providesContentAdapter(){
        return new ContentAdapter();
    }
}
