package com.morozov.imagesearch.screen.ImageDetail.mvp;

import com.morozov.imagesearch.api.GettyImagesApi;
import com.morozov.imagesearch.api.model.BaseModel;
import com.morozov.imagesearch.api.model.ImageResponse;
import com.morozov.imagesearch.api.model.MetadataResponse;
import com.morozov.imagesearch.screen.ImageDetail.ImageDetailActivity;

import io.reactivex.Observable;

public class ImageDetailModel extends BaseModel {

    private ImageDetailActivity context;

    public ImageDetailModel(GettyImagesApi api, ImageDetailActivity imageDetailActivity) {
        this.api = api;
        this.context = imageDetailActivity;
    }

    public Observable<MetadataResponse> getImageMetadata(String id) {
        return api.getImageMetadata(id);
    }

    public Observable<ImageResponse> getSimilarImages(String id) {
        return api.getSimilarImages(id);
    }
}
