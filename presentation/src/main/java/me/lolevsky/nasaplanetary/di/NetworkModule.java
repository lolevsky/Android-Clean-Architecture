package me.lolevsky.nasaplanetary.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lolevsky.nasaplanetary.BuildConfig;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.data.net.NasaService;
import me.lolevsky.nasaplanetary.data.net.TokenInterceptor;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String BASE_URL = "https://api.nasa.gov";

    @Singleton
    @Provides Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides OkHttpClient provideOkHttpProxy(MainApplication mainApplication) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                                       .header("Cache-Control", String.format("max-age=%d, only-if-cached, " +
                                                                              "max-stale=%d", 60*4, 0))
                                       .build();
            }
        };

        Cache cache = new Cache(mainApplication.getCacheDir(), 1024 * 1024 * 10);

        OkHttpClient initialOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(logging)
                .addNetworkInterceptor(rewriteCacheControlInterceptor)
                .cache(cache)
                .build();

        return initialOkHttpClient;
    }

    @Singleton
    @Provides NasaService provideNasaService(OkHttpClient client,
                                             Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(NasaService.class);
    }
}
