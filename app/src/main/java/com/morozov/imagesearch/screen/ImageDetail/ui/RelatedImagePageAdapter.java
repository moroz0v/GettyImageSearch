package com.morozov.imagesearch.screen.ImageDetail.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.morozov.imagesearch.R;
import com.morozov.imagesearch.api.model.ImageResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RelatedImagePageAdapter extends PagerAdapter {

    List<ImageResult> models;

    @Inject
    public RelatedImagePageAdapter() {
        models = new ArrayList<ImageResult>();
    }

    public void setViewModels(ArrayList<ImageResult> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater =
                (LayoutInflater) container
                        .getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = layoutInflater.inflate(R.layout.page_related_image, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.related_image);
        ImageResult imageResult = models.get(position);
        Picasso.with(itemView.getContext())
                .load(imageResult.getThumbUri())
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
