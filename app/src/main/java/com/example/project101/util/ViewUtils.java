package com.example.project101.util;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {

    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
