package com.morozov.imagesearch.screen.ImageGrid.injection;

import com.morozov.imagesearch.injection.AppComponent;
import com.morozov.imagesearch.screen.ImageGrid.ImageGridActivity;

import dagger.Component;

@ImageGridScope
@Component(modules = {ImageGridContextModule.class, ImageGridModule.class}, dependencies = {AppComponent.class})
public interface ImageGridComponent {

    void inject(ImageGridActivity activity);

}
