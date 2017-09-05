package com.morozov.imagesearch.screen.ImageDetail.injection;

import com.morozov.imagesearch.injection.AppComponent;
import com.morozov.imagesearch.screen.ImageDetail.ImageDetailActivity;

import dagger.Component;


@ImageDetailScope
@Component(modules = {ImageDetailContextModule.class, ImageDetailModule.class}, dependencies = {AppComponent.class})
public interface ImageDetailComponent {

    void inject(ImageDetailActivity activity);

}

