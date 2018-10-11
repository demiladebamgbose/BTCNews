package net.androidbootcamp.btcnews.dagger;

import net.androidbootcamp.btcnews.activities.MainActivity;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
