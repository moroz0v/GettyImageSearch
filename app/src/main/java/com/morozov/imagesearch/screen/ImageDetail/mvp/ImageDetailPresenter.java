package com.morozov.imagesearch.screen.ImageDetail.mvp;

import com.morozov.imagesearch.R;
import com.morozov.imagesearch.api.model.ImageResult;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageDetailPresenter {


    private final int numberOfSimiarImages = 3;

    private ImageDetailModel model;
    private ImageDetailView view;
    private CompositeDisposable subscriptions;
    private ImageResult imageResult;

    public ImageDetailPresenter(ImageDetailModel model,
                                ImageDetailView view,
                                CompositeDisposable subscriptions) {
        this.model = model;
        this.view = view;
        this.subscriptions = subscriptions;
    }

    public void onCreate(ImageResult imageResult) {
        this.imageResult = imageResult;
        subscriptions.add(getImageDetails(this.imageResult));
        subscriptions.add(getImageMetadata(this.imageResult));
        subscriptions.add(startFlippingViewPager());
    }

    public Disposable getImageDetails(ImageResult result) {
        return model
                .getSimilarImages(result.getId())
                .subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                .observeOn(AndroidSchedulers.mainThread())
                .map(similarImages -> {

                    if (similarImages.getImages().size() == 0) {
                        view.displayErrorMessage(R.string.no_results);
                        return similarImages.getImages();
                    }

                    int startIndex = 1;
                    int endIndex = startIndex + numberOfSimiarImages;
                    if (startIndex > endIndex || endIndex >= similarImages.getImages().size() - 1)
                        endIndex = similarImages.getImages().size() - 1;

                    return similarImages.getImages().subList(startIndex, endIndex);
                })
                .subscribe(similarImages -> view.dispayResults(new ArrayList<>(similarImages)));
    }

    public Disposable getImageMetadata(ImageResult result) {
        return model
                .getImageMetadata(result.getId())
                .subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(metadataResponse -> view.displayMetadata(metadataResponse, imageResult));
    }

    public Disposable startFlippingViewPager() {
        return Observable
                .interval(500, 2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> view.startFlipping());
    }

    public void onDestroy() {
        subscriptions.clear();
    }
}
