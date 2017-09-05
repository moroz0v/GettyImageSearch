package com.morozov.imagesearch.screen.ImageGrid.ui;

import android.view.View;

import com.morozov.imagesearch.R;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.util.LongPressGestureDetector;

public class GettyImageViewModel extends BaseViewModel<GettyImageViewHolder> implements LongPressGestureDetector.Listener {
    private final ImageResult imageResult;
    private final Listener listener;
    private final int id;

    public GettyImageViewModel(int id, ImageResult imageResult, Listener listener) {
        super(R.layout.getty_image_layout);
        this.id = id;
        this.imageResult = imageResult;
        this.listener = listener;
    }

    @Override
    public GettyImageViewHolder createItemViewHolder(View view) {
        return new GettyImageViewHolder(view);
    }

    @Override
    public void bindViewHolder(GettyImageViewHolder holder) {
        holder.bind(imageResult, this);
    }

    @Override
    public ViewModelType getViewType() {
        return ViewModelType.GETTY_IMAGE;
    }

    @Override
    public void onLongPress() {
        if (listener != null) {
            listener.onImageLongPress(imageResult);
        }
    }

    public interface Listener {
        void onImageLongPress(ImageResult imageResult);
    }
}
