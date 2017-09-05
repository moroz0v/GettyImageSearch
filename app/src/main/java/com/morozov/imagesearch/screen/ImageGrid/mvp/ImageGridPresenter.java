package com.morozov.imagesearch.screen.ImageGrid.mvp;

import com.morozov.imagesearch.R;
import com.morozov.imagesearch.api.model.ImageResponse;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.screen.ImageGrid.ui.BaseViewModel;
import com.morozov.imagesearch.screen.ImageGrid.ui.GettyImageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageGridPresenter {

    private ImageGridModel model;
    private ImageGridView view;
    private CompositeDisposable subscriptions;
    private GettyImageFactory imageFactory;

    public ImageGridPresenter(ImageGridModel model,
                              ImageGridView view,
                              CompositeDisposable subscriptions,
                              GettyImageFactory imageFactory) {
        this.model = model;
        this.view = view;
        this.subscriptions = subscriptions;
        this.imageFactory = imageFactory;
    }

    public void onCreate() {
        subscriptions.add(respondToClick());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Disposable respondToClick() {
        return view
                .searchObserver()
                .subscribe(searchQuery ->
                        model.provideSearchResults(searchQuery)
                                .subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                                .observeOn(AndroidSchedulers.mainThread())
                                .map(imageResponse -> {
                                    if (imageResponse.getImages().size() == 0) {
                                        view.displayErrorMessage(R.string.no_results);
                                    }
                                    return imageResponse;
                                })
                                .subscribe(imageResponse -> displaySearchResults(imageResponse),
                                        throwable -> {
                                            view.displayErrorMessage(throwable.getLocalizedMessage());
                                        }
                                ));
    }

    private void displaySearchResults(ImageResponse response) {
        List<BaseViewModel> viewModels = new ArrayList<>();
        int i = 0;
        for (ImageResult imageResult : response.getImages()) {
            viewModels.add(
                    imageFactory.createGettyImageViewModel(
                            i++, imageResult, this::onImageLongPress));
        }
        view.dispayResults(viewModels);
    }

    public void onImageLongPress(ImageResult imageResult) {
        model.displayImageDetailActivity(imageResult);
    }

}
