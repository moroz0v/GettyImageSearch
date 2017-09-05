package com.morozov.imagesearch.screen.ImageGrid.mvp;

import com.morozov.imagesearch.api.GettyImagesApi;
import com.morozov.imagesearch.api.model.BaseModel;
import com.morozov.imagesearch.api.model.ImageResponse;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.screen.ImageGrid.ImageGridActivity;

import io.reactivex.Observable;


public class ImageGridModel extends BaseModel {

    public static final String FIELDS = "id,title,thumb";
    public static final String SORT_ORDER = "best";

    private ImageGridActivity imageGridContext;

    public ImageGridModel(GettyImagesApi api, ImageGridActivity imageGridContext) {
        this.api = api;
        this.imageGridContext = imageGridContext;
    }

    public Observable<ImageResponse> provideSearchResults(String searchQuery) {
        return api.searchImages(searchQuery, FIELDS, SORT_ORDER);
    }

    public void displayImageDetailActivity(ImageResult imageResult) {
        imageGridContext.displayImageDetailActivity(imageResult);
    }
}
