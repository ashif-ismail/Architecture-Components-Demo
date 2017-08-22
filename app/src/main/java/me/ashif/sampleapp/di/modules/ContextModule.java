package me.ashif.sampleapp.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Ashif on 22/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class ContextModule {

  private Context mContext;

  public ContextModule(Context mContext) {
    this.mContext = mContext;
  }

  @Singleton
  @Provides
  public Context provideContext() {
    return mContext;
  }
}
