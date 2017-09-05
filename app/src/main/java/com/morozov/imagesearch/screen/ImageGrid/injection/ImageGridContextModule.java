package com.morozov.imagesearch.screen.ImageGrid.injection;

import com.morozov.imagesearch.screen.ImageGrid.ImageGridActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageGridContextModule {


    ImageGridActivity imageGridContext;

    public ImageGridContextModule(ImageGridActivity context) {
        this.imageGridContext = context;
    }

    @ImageGridScope
    @Provides
    ImageGridActivity provideImageGridContext() {
        return imageGridContext;
    }


}