package com.example.project101.presentation.onboard;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.project101.domain.model.onboard.OnboardDetails;
import com.example.project101.presentation.home.HomeActivity;
import com.example.project101.util.Constant;
import com.example.project101.util.ViewUtils;

public class OnboardViewModel extends ViewModel {
    public final OnboardDetails details = new OnboardDetails();


    public final void continueRegister(View view) {
        if (details.getFullName().trim().isEmpty()) {
            ViewUtils.showSnackBar(view, "Enter full name properly");
        } else if (details.getMobile().trim().isEmpty()) {
            ViewUtils.showSnackBar(view, "Enter mobile properly");
        } else if (details.getEmail().trim().isEmpty()) {
            ViewUtils.showSnackBar(view, "Enter email properly");
        } else if (details.getUriString() == null || details.getUriString().trim().isEmpty()) {
            ViewUtils.showSnackBar(view, "Select a valid Image");
        } else {
            Intent intent = new Intent(view.getContext(), HomeActivity.class);
            intent.putExtra(Constant.DETAILS, details);
            view.getContext().startActivity(intent);
        }
    }
}
