package com.morozov.imagesearch.screen.ImageGrid.ui;

import com.morozov.imagesearch.api.model.ImageResult;

import javax.inject.Inject;

public class GettyImageFactory {
    @Inject
    public GettyImageFactory() {
    }

    public GettyImageViewModel createGettyImageViewModel(int id, ImageResult imageResult, GettyImageViewModel.Listener listener) {
        return new GettyImageViewModel(id, imageResult, listener);
    }
}
