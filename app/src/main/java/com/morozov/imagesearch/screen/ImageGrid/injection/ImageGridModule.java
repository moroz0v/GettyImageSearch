package com.morozov.imagesearch.screen.ImageGrid.injection;

import com.morozov.imagesearch.api.GettyImagesApi;
import com.morozov.imagesearch.screen.ImageGrid.ImageGridActivity;
import com.morozov.imagesearch.screen.ImageGrid.mvp.ImageGridModel;
import com.morozov.imagesearch.screen.ImageGrid.mvp.ImageGridPresenter;
import com.morozov.imagesearch.screen.ImageGrid.mvp.ImageGridView;
import com.morozov.imagesearch.screen.ImageGrid.ui.GettyImageFactory;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ImageGridModule {

    @ImageGridScope
    @Provides
    ImageGridPresenter providePresenter(ImageGridModel model, ImageGridView view, GettyImageFactory factory) {
        return new ImageGridPresenter(model, view, new CompositeDisposable(), factory);
    }


    @ImageGridScope
    @Provides
    ImageGridView provideSplashView(ImageGridActivity context) {
        return new ImageGridView(context);
    }


    @ImageGridScope
    @Provides
    ImageGridModel provideSplashModel(GettyImagesApi api, ImageGridActivity ctx) {
        return new ImageGridModel(api, ctx);
    }
}
