package com.example.project101.domain.repository;

import com.example.project101.domain.model.response.Response;

public interface Repository {

    void getRandomDogImages(Response<String> response);
}
