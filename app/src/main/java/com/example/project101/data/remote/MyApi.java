package com.example.project101.data.remote;


import com.example.project101.data.remote.dto.RandomDogImage;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface MyApi {

    @GET("api/breeds/image/random")
    Observable<RandomDogImage> getRandomDogImages();
}
