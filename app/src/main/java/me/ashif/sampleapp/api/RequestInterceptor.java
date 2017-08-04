package me.ashif.sampleapp.api;

import java.io.IOException;

import me.ashif.sampleapp.util.AppConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", AppConstants.API_KEY)
                .build();

        Request request = originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
