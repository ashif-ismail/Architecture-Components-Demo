package me.ashif.sampleapp.di.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.ashif.sampleapp.api.ApiService;
import me.ashif.sampleapp.api.LoggingInterceptor;
import me.ashif.sampleapp.di.components.VMSubComponent;
import me.ashif.sampleapp.util.AppConstants;
import me.ashif.sampleapp.util.AppUtils;
import me.ashif.sampleapp.view.ui.ViewModelFactory;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module(subcomponents = VMSubComponent.class)
public class AppModule {
    /*
    complete app level dependencies should be included here
     */

    @Singleton
    @Provides
    public ApiService providesApiService(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService.class);
    }
    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(LoggingInterceptor requestInterceptor, Cache cache) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(AppConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(AppConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(requestInterceptor);
        okHttpClient.cache(cache);
        return okHttpClient.build();
    }

    @Singleton
    @Provides
    public Cache providesHttpCache(Application application){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    public LoggingInterceptor providesRequestInterceptor() {
        return new LoggingInterceptor();
    }

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

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            VMSubComponent.Builder viewModelSubComponent) {

        return new ViewModelFactory(viewModelSubComponent.build());
    }

    @Provides
    @Singleton
    public Picasso providesPicasso(Application application,OkHttpClient okHttpClient){
        return new Picasso.Builder(application)
            .downloader(new OkHttp3Downloader(okHttpClient))
            .build();
    }

}
