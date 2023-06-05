package com.example.project101.presentation.home;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project101.domain.model.onboard.OnboardDetails;
import com.example.project101.domain.model.response.Response;
import com.example.project101.domain.model.state.ScreenState;
import com.example.project101.domain.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final Repository repository;
    public OnboardDetails details;
    private MutableLiveData<ScreenState> _screenState = new MutableLiveData<>(ScreenState.IDLE);
    public LiveData<ScreenState> screenState = _screenState;

    private MutableLiveData<String> _screenContent = new MutableLiveData<>();
    public LiveData<String> screenContent = _screenContent;

    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
        getImages();
    }


    public void getImages(){
        _screenState.setValue(ScreenState.PROGRESS);
        repository.getRandomDogImages(new Response<String>() {
            @Override
            public void result(String message) {
                _screenContent.setValue(message);
                _screenState.setValue(ScreenState.SUCCESS);
            }
            @Override
            public void message(String error) {
                _screenState.setValue(ScreenState.ERROR);
                _screenContent.setValue(error);
            }
        });
    }


    public void changeImage(View view){
        getImages();
    }

}
