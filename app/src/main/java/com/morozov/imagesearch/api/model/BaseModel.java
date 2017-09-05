package com.morozov.imagesearch.api.model;

import com.google.gson.Gson;
import com.morozov.imagesearch.api.GettyImagesApi;

import java.lang.reflect.Type;

public class BaseModel {

    public GettyImagesApi api;

    public String toJSONString() {
        return new Gson().toJson(this);
    }

    public static <T> T getObject(String jsonString, Type typeOfT) {
        return new Gson().fromJson(jsonString, typeOfT);
    }
}
