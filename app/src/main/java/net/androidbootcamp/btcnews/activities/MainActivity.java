package net.androidbootcamp.btcnews.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import net.androidbootcamp.btcnews.BtcNewsApp;
import net.androidbootcamp.btcnews.R;
import net.androidbootcamp.btcnews.adapters.ArticleAdapter;
import net.androidbootcamp.btcnews.viewModel.MainViewModel;
import net.androidbootcamp.btcnews.viewModel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelFactory mViewModelFactory;
    private MainViewModel mMainViewModel;
    private Toolbar mToolbar;
    private ArticleAdapter mAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // inject AppComponent
        ((BtcNewsApp)getApplication()).getAppComponent().inject(this);

        // instantiate viewmodel
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);


        mAdapter = new ArticleAdapter(this);


        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);


        mMainViewModel.getArticles().observe(this, (articles -> {
            mAdapter.setArticles(articles);
        }));
    }
}
