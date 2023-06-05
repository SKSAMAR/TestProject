package com.example.project101.data.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;

import com.example.project101.data.remote.MyApi;
import com.example.project101.data.remote.dto.RandomDogImage;
import com.example.project101.domain.model.response.Response;
import com.example.project101.domain.repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryImpl implements Repository {
    private final MyApi myApi;
    public RepositoryImpl(MyApi myApi) {
        this.myApi = myApi;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getRandomDogImages(Response<String> response) {
        myApi.getRandomDogImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res->{
                    if (res.getStatus().equalsIgnoreCase("success")){
                        response.result(res.getMessage());
                    }else{
                        response.message(res.getMessage());
                    }
                }, error->{
                    response.message(error.getLocalizedMessage());
                });
    }
}
