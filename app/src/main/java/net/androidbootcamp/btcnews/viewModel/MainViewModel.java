package net.androidbootcamp.btcnews.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import net.androidbootcamp.btcnews.db.Article;
import net.androidbootcamp.btcnews.repositories.ArticleRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private ArticleRepository mRepository;

    private LiveData<List<Article>> mArticles;
    public LiveData<List<Article>> getArticles() {
        return mArticles;
    }

    public MainViewModel (ArticleRepository repository) {
        mRepository = repository;
        mArticles = Transformations.map(mRepository.getArticles(), (articles) -> articles);
    }

    public void loadArticles () {
        mRepository.fetchData();
    }


}
