package com.morozov.imagesearch;

import android.app.Application;

import com.morozov.imagesearch.injection.AppComponent;
import com.morozov.imagesearch.injection.AppModule;
import com.morozov.imagesearch.injection.DaggerAppComponent;

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
