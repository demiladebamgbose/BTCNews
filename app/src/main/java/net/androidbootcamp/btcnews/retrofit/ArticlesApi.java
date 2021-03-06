package net.androidbootcamp.btcnews.retrofit;

import net.androidbootcamp.btcnews.retrofit.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesApi {

    @GET("/v2/everything")
    Call<ArticleResponse> getArticles(@Query("q") String query,
                                      @Query(value = "from", encoded = true) String date,
                                      @Query("sortBy") String feild,
                                      @Query("apiKey") String apiKey);
}
