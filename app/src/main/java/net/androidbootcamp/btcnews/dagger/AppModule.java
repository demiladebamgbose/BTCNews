package net.androidbootcamp.btcnews.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.androidbootcamp.btcnews.BtcNewsApp;
import net.androidbootcamp.btcnews.retrofit.ArticlesApi;
import net.androidbootcamp.btcnews.retrofit.model.Article;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.nio.file.attribute.AclEntry.newBuilder;

@Module
public class AppModule {
    private static final String baseUrl = "https://newsapi.org/v2/";
    private BtcNewsApp mBtcNewsApp;

    public AppModule(BtcNewsApp btcNewsApp) {
        this.mBtcNewsApp = btcNewsApp;
    }


    @Singleton
    @Provides
    OkHttpClient providesOkhttp() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    ArticlesApi providesArticlesApi (Retrofit retrofit) {
        return retrofit.create(ArticlesApi.class);
    }
}
