package me.lolevsky.nasaplanetary.data.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private static String AUTHORIZATION = "api_key";
    private static String TOKEN = "DEMO_KEY";

    @Override public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                                     .addQueryParameter(AUTHORIZATION, TOKEN)
                                     .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                                                 .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
