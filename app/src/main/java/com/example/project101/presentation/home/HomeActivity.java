package com.example.project101.presentation.home;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.project101.R;
import com.example.project101.databinding.ActivityHomeBinding;
import com.example.project101.domain.model.onboard.OnboardDetails;
import com.example.project101.domain.model.state.ScreenState;
import com.example.project101.util.Constant;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializer();
        setContentView(binding.getRoot());

    }

    private void initializer() {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.details = (OnboardDetails) getIntent().getSerializableExtra(Constant.DETAILS);
        binding.setHomeViewModel(viewModel);
        startObserving();
    }

    private void startObserving() {
        viewModel.screenState.observe(this, result -> {
            if (result != null) {
                if (result.equals(ScreenState.PROGRESS)) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        });

        viewModel.screenContent.observe(this, result -> {
            if (result != null) {
                ScreenState screenState = viewModel.screenState.getValue();
                if (screenState != null && screenState.equals(ScreenState.ERROR)) {
                    binding.errorMessage.setText(result);
                    binding.errorMessage.setVisibility(View.VISIBLE);
                } else if (screenState != null && screenState.equals(ScreenState.SUCCESS)) {
                    binding.errorMessage.setText("");
                    binding.errorMessage.setVisibility(View.GONE);
                }
                Glide.with(binding.imageContent).load(result).error(R.drawable.no_image).placeholder(R.drawable.loading).into(binding.imageContent);
            }
        });
    }
}
