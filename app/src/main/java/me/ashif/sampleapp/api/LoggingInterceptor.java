package me.ashif.sampleapp.api;

import java.io.IOException;
import me.ashif.sampleapp.util.AppConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class LoggingInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
       /* Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", AppConstants.API_KEY)
                .build();

        Request request = originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
 */
    Request request = chain.request();
    HttpUrl originalUrl = request.url();

    long sendingTime = System.nanoTime();
    Timber.d(String.format("Sending request %s on %s%n%s",
        request.url(), chain.connection(), request.headers()));

    HttpUrl urlToHit = originalUrl.newBuilder()
        .addQueryParameter("api_key", AppConstants.API_KEY)
        .build();

    Request serverRequest = request.newBuilder().url(urlToHit).build();
    Response response = chain.proceed(serverRequest);

    long responseTime = System.nanoTime();
    Timber.d(String.format("Received response for %s in %.1fms%n%s",
        response.request().url(), (responseTime - sendingTime) / 1e6d,
        response.headers()));

    return chain.proceed(serverRequest);
  }
}
