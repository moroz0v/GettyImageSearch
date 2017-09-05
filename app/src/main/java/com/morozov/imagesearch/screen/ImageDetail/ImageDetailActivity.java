package com.morozov.imagesearch.screen.ImageDetail;

import android.os.Bundle;

import com.morozov.imagesearch.App;
import com.morozov.imagesearch.api.model.BaseModel;
import com.morozov.imagesearch.api.model.ImageResult;
import com.morozov.imagesearch.screen.BaseActivity;
import com.morozov.imagesearch.screen.ImageDetail.injection.DaggerImageDetailComponent;
import com.morozov.imagesearch.screen.ImageDetail.injection.ImageDetailContextModule;
import com.morozov.imagesearch.screen.ImageDetail.mvp.ImageDetailPresenter;
import com.morozov.imagesearch.screen.ImageDetail.mvp.ImageDetailView;

import javax.inject.Inject;

/**
 * Created by sergeymorozov on 9/1/17.
 */

public class ImageDetailActivity extends BaseActivity {

    @Inject
    ImageDetailView view;
    @Inject
    ImageDetailPresenter imageDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerImageDetailComponent
                .builder()
                .appComponent(App.getInstance().getAppComponent())
                .imageDetailContextModule(new ImageDetailContextModule(this))
                .build()
                .inject(this);

        setContentView(view.constructView());

        ImageResult result =
                BaseModel.getObject(
                getIntent().getExtras().getString(keyImageResult), ImageResult.class);

        imageDetailPresenter.onCreate(result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imageDetailPresenter.onDestroy();
    }
}
