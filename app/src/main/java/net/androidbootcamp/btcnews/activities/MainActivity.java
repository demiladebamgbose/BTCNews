package net.androidbootcamp.btcnews.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.androidbootcamp.btcnews.BtcNewsApp;
import net.androidbootcamp.btcnews.R;
import net.androidbootcamp.btcnews.viewModel.MainViewModel;
import net.androidbootcamp.btcnews.viewModel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Inject
    private ViewModelFactory mViewModelFactory;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // inject AppComponent
        ((BtcNewsApp)getApplication()).getAppComponent().inject(this);
        // instantiate viewmodel
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);

    }
}
