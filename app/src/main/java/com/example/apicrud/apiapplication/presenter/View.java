package com.example.apicrud.apiapplication.presenter;

import com.example.apicrud.apiapplication.model.getall.GetResponse;

public interface View {
    void getSuccess(GetResponse list);
    void setToast(String message);
    void onError(String errorMessage);
    void onFailure(String failureMessage);
}
