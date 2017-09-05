package com.morozov.imagesearch.screen.ImageGrid.mvp;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.morozov.imagesearch.R;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.databinding.ActivityImageGridBinding;
import com.morozov.imagesearch.screen.ImageGrid.ImageGridActivity;
import com.morozov.imagesearch.screen.ImageGrid.ui.BaseViewModel;
import com.morozov.imagesearch.screen.ImageGrid.ui.ImageGridAdapter;
import com.morozov.imagesearch.util.SpaceItemDecoration;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ImageGridView {

    private static final int numberOfColums = 3;

    @Inject
    ImageGridAdapter adapter;
    ActivityImageGridBinding binding;
    PublishSubject<String> searchObserver;
    int decoratorSpace;


    public ImageGridView(ImageGridActivity context) {
        binding = ActivityImageGridBinding.inflate(context.getLayoutInflater());

        decoratorSpace = (int) context.getResources().getDimension(R.dimen.image_space);
        binding.searchPhrase.setVisibility(View.VISIBLE);
        searchObserver = PublishSubject.create();

        binding.searchPhrase.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchObserver.onNext(binding.searchPhrase.getText().toString());
                return true;
            }
            return false;
        });

        adapter = new ImageGridAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(context, numberOfColums));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(
                new SpaceItemDecoration(
                        decoratorSpace,
                        decoratorSpace,
                        decoratorSpace,
                        decoratorSpace));
    }

    public Observable<String> searchObserver() {
        return searchObserver;
    }

    public void displayErrorMessage(String message) {
        Toast.makeText(binding.getRoot().getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void displayErrorMessage(int resourceID) {
        String error = binding.getRoot().getContext().getResources().getString(resourceID);
        displayErrorMessage(error);
    }

    public void dispayResults(List<BaseViewModel> results) {
        adapter.setViewModels(results);
    }

    private void updateImages(List<ImageResult> images) {

    }

    public View constructView() {
        return binding.getRoot();
    }

    private void search() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
}
