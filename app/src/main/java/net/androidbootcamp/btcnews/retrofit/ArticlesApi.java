package net.androidbootcamp.btcnews.retrofit;

import net.androidbootcamp.btcnews.retrofit.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesApi {

    @GET("/everything")
    Call<ArticleResponse> getArticles(@Query("q") String query,
                                      @Query("from") String date,
                                      @Query("sortBy") String feild,
                                      @Query("apiKey") String apiKey);
}
