package net.androidbootcamp.btcnews;

import android.app.Application;

import net.androidbootcamp.btcnews.dagger.AppComponent;
import net.androidbootcamp.btcnews.dagger.AppModule;
import net.androidbootcamp.btcnews.dagger.DaggerAppComponent;

public class BtcNewsApp extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
