package com.morozov.imagesearch.screen.ImageDetail.injection;

import com.morozov.imagesearch.api.GettyImagesApi;
import com.morozov.imagesearch.screen.ImageDetail.ImageDetailActivity;
import com.morozov.imagesearch.screen.ImageDetail.mvp.ImageDetailModel;
import com.morozov.imagesearch.screen.ImageDetail.mvp.ImageDetailPresenter;
import com.morozov.imagesearch.screen.ImageDetail.mvp.ImageDetailView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ImageDetailModule {

    @ImageDetailScope
    @Provides
    ImageDetailPresenter providePresenter(ImageDetailModel model, ImageDetailView view) {
        return new ImageDetailPresenter(model, view, new CompositeDisposable());
    }


    @ImageDetailScope
    @Provides
    ImageDetailView provideView(ImageDetailActivity context) {
        return new ImageDetailView(context);
    }


    @ImageDetailScope
    @Provides
    ImageDetailModel provideModel(GettyImagesApi api, ImageDetailActivity ctx) {
        return new ImageDetailModel(api, ctx);
    }
}

