package com.morozov.imagesearch.screen.ImageGrid;

import android.content.Intent;
import android.os.Bundle;

import com.morozov.imagesearch.App;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.screen.BaseActivity;
import com.morozov.imagesearch.screen.ImageDetail.ImageDetailActivity;
import com.morozov.imagesearch.screen.ImageGrid.injection.DaggerImageGridComponent;
import com.morozov.imagesearch.screen.ImageGrid.injection.ImageGridContextModule;
import com.morozov.imagesearch.screen.ImageGrid.mvp.ImageGridPresenter;
import com.morozov.imagesearch.screen.ImageGrid.mvp.ImageGridView;

import javax.inject.Inject;

public class ImageGridActivity extends BaseActivity {

    @Inject
    ImageGridView view;
    @Inject
    ImageGridPresenter imageGridPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerImageGridComponent
                .builder()
                .appComponent(App.getInstance().getAppComponent())
                .imageGridContextModule(new ImageGridContextModule(this))
                .build()
                .inject(this);

        setContentView(view.constructView());
        imageGridPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imageGridPresenter.onDestroy();
    }

    public void displayImageDetailActivity(ImageResult imageResult) {

        Intent intent = new Intent(this, ImageDetailActivity.class);
        intent.putExtra(keyImageResult, imageResult.toJSONString());
        startActivity(intent);
    }
}
