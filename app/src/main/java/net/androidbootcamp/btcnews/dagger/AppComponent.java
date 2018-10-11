package net.androidbootcamp.btcnews.dagger;

import net.androidbootcamp.btcnews.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
