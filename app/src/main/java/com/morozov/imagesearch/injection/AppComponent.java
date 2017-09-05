package com.morozov.imagesearch.injection;


import com.morozov.imagesearch.App;
import com.morozov.imagesearch.api.GettyImagesApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, GettyImagesApi.class})
public interface AppComponent {

    void inject(App app);

    GettyImagesApi apiService();
}