package com.morozov.imagesearch.screen.ImageGrid.ui;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.morozov.imagesearch.R;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.util.LongPressGestureDetector;


public class GettyImageViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public GettyImageViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.image_view);
    }

    public void bind(ImageResult imageResult, LongPressGestureDetector.Listener listener) {
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(itemView.getContext(), new LongPressGestureDetector(listener));

        itemView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        Picasso.with(itemView.getContext())
                .load(imageResult.getThumbUri())
                .into(imageView);
    }
}
