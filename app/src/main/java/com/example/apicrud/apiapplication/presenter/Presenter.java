package com.example.apicrud.apiapplication.presenter;

import com.example.apicrud.apiapplication.connection.BaseApp;
import com.example.apicrud.apiapplication.model.create.PostResponse;
import com.example.apicrud.apiapplication.model.getall.GetResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {
    private View view;
    public Presenter(View view) {
        this.view = view;
    }

    public void getAllItems() {
        BaseApp.service.getAllItems().enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if (response.isSuccessful())
                    view.getSuccess(response.body());
                else
                    view.onError(response.message());
            }
            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                view.onFailure(t.getMessage());
            }
        });
    }
    public void updateItems(String id, String name, String description) {
        BaseApp.service.updateDataItems(id,name,description).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful())
                    view.setToast(response.message());
                else
                    view.onError(response.message());
                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                view.onFailure(t.getMessage());
                }
        });
    }

    public void deleteItems(String id) {BaseApp.service.deleteDataItems(id).enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful())
                view.setToast(response.message());
            else
                view.onError(response.message());
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            view.onFailure(t.getMessage());
            }
    });
    }

    public void createItems(String name, String description) {
        BaseApp.service.createItems(name,description).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful())
                    view.setToast(response.message());
                else
                    view.onError(response.message());
                }
                @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                view.onFailure(t.getMessage());
                }
                });
    }
}
