package com.morozov.imagesearch.screen.ImageDetail.injection;

import com.morozov.imagesearch.screen.ImageDetail.ImageDetailActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class ImageDetailContextModule {

    ImageDetailActivity imageDetailContext;

    public ImageDetailContextModule(ImageDetailActivity context) {
        this.imageDetailContext = context;
    }

    @ImageDetailScope
    @Provides
    ImageDetailActivity provideImageDetailContext() {
        return imageDetailContext;
    }

}
