package com.example.project101.domain.model.response;

public interface Response<T> {

    void result(T message);
    void message(String error);
}
