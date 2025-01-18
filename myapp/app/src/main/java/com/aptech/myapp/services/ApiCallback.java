package com.aptech.myapp.services;
public interface ApiCallback<T> {
    void onSuccess(T response);

    void onError(String errorMessage);
}