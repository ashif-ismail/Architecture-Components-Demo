package me.ashif.sampleapp.di.modules;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ashif.sampleapp.network.ApiService;
import me.ashif.sampleapp.network.RequestInterceptor;
import me.ashif.sampleapp.util.AppConstants;
import me.ashif.sampleapp.util.AppUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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
    public OkHttpClient providesOkHttpClient(RequestInterceptor requestInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(AppConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(AppConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(requestInterceptor);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    public RequestInterceptor providesRequestInterceptor(){
        return new RequestInterceptor();
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

}
