package net.androidbootcamp.btcnews.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import net.androidbootcamp.btcnews.repositories.ArticleRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    ArticleRepository mRepository;


    public ViewModelFactory (ArticleRepository articleRepository) {
        this.mRepository = articleRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mRepository);
        } else return null;
    }
}