package net.androidbootcamp.btcnews.dagger;

import android.arch.persistence.room.Room;

import com.google.gson.GsonBuilder;

import net.androidbootcamp.btcnews.BtcNewsApp;
import net.androidbootcamp.btcnews.db.Article;
import net.androidbootcamp.btcnews.db.ArticleDatabase;
import net.androidbootcamp.btcnews.repositories.ArticleRepository;
import net.androidbootcamp.btcnews.retrofit.ArticlesApi;
import net.androidbootcamp.btcnews.viewModel.ViewModelFactory;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {
    private static final String baseUrl = "https://newsapi.org/v2/";
    private BtcNewsApp mBtcNewsApp;

    public AppModule(BtcNewsApp btcNewsApp) {
        this.mBtcNewsApp = btcNewsApp;
    }

    @Provides
    @Singleton
    BtcNewsApp providesBtcNewsApp() {
        return mBtcNewsApp;
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

    @Singleton
    @Provides
    ArticleDatabase providesArticleDatabase(BtcNewsApp btcNewsApp) {
        return Room.databaseBuilder(btcNewsApp.getApplicationContext(),
                ArticleDatabase.class, Article.getTableName())
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    ArticleRepository providesArticleRepository(ArticlesApi articlesApi, ArticleDatabase articleDatabase, BtcNewsApp btcNewsApp) {
        return new ArticleRepository(articleDatabase, articlesApi, btcNewsApp);
    }

    @Singleton
    @Provides
    ViewModelFactory providesViewModelFactory (ArticleRepository articleRepository) {
        return new ViewModelFactory(articleRepository);
    }
}
