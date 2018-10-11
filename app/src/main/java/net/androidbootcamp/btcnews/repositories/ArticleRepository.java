package net.androidbootcamp.btcnews.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import net.androidbootcamp.btcnews.BtcNewsApp;
import net.androidbootcamp.btcnews.R;
import net.androidbootcamp.btcnews.db.Article;
import net.androidbootcamp.btcnews.db.ArticleDatabase;
import net.androidbootcamp.btcnews.retrofit.ArticlesApi;
import net.androidbootcamp.btcnews.retrofit.model.ArticleResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private ArticleDatabase mArticleDatabase;
    private ArticlesApi mApi;
    private Context mContext;

    private static final String SORT = "publishedAt";
    private static final String BTC = "bitcoin";

    public ArticleRepository(ArticleDatabase articleDatabase, ArticlesApi api, BtcNewsApp btcNewsApp) {
        mArticleDatabase = articleDatabase;
        mApi = api;
        mContext = btcNewsApp.getApplicationContext();
    }

    public LiveData<List<Article>> getArticles() {
        fetchData();
        return mArticleDatabase.articleDao().getArticles();
    }

    public void fetchData() {
        String key = mContext.getResources().getString(R.string.api_key);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "YYYY-MM-DD", Locale.getDefault());
        String date = simpleDateFormat.format(calendar.getTime());



        mApi.getArticles(BTC, date, SORT, key).enqueue(
                new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                        if (response.code() == 200 && response.body().getStatus().equals("ok")) {
                            List<Article> articlesList = new ArrayList<>();

                            for (net.androidbootcamp.btcnews.retrofit.model.Article article : response.body().getArticles()) {
                                articlesList.add(new Article(article));
                            }

                            mArticleDatabase.articleDao().emptyTable();
                            mArticleDatabase.articleDao().addNews(articlesList);
                        }
                    }
                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        // TODO: Handle on failure
                    }
                }
        );
    }

}
