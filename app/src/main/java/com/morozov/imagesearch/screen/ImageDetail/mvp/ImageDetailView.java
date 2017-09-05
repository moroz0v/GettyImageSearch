package com.morozov.imagesearch.screen.ImageDetail.mvp;

import android.view.View;
import android.widget.Toast;

import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.api.model.MetadataResponse;
import com.morozov.imagesearch.databinding.ActivityImageDetailsBinding;
import com.morozov.imagesearch.screen.BaseView;
import com.morozov.imagesearch.screen.ImageDetail.ImageDetailActivity;
import com.morozov.imagesearch.screen.ImageDetail.ui.RelatedImagePageAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.R.id.message;

public class ImageDetailView extends BaseView {

    ActivityImageDetailsBinding binding;
    RelatedImagePageAdapter adapter;

    public ImageDetailView(ImageDetailActivity context) {
        binding = ActivityImageDetailsBinding.inflate(context.getLayoutInflater());
        adapter = new RelatedImagePageAdapter();
        binding.relatedImagesPager.setAdapter(adapter);
    }

    public void dispayResults(ArrayList<ImageResult> results) {
        binding.relatedImagesPager.setVisibility(View.VISIBLE);
        adapter.setViewModels(results);
    }

    public void startFlipping() {
        int nextPage = binding.relatedImagesPager.getCurrentItem() + 1;
        if (nextPage >= adapter.getCount())
            nextPage = 0;

        binding.relatedImagesPager.setCurrentItem(nextPage);
    }

    public void displayMetadata(MetadataResponse response, ImageResult originalImage) {
        binding.imageMatadata.setVisibility(View.VISIBLE);
        binding.setMetadata(response.getMetadata().get(0));
        Picasso.with(binding.getRoot().getContext())
                .load(originalImage.getThumbUri())
                .into(binding.requestedImage);
    }

    public View constructView() {
        return binding.getRoot();
    }

    @Override
    public void displayErrorMessage(String message) {
        Toast.makeText(binding.getRoot().getContext(), message, Toast.LENGTH_LONG);
    }

    @Override
    public void displayErrorMessage(int resourseID) {
        Toast.makeText(binding.getRoot().getContext(), resourseID, Toast.LENGTH_LONG);
    }
}
