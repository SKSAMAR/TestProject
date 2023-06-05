package com.example.project101.util;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.project101.R;

public class BindingUtil {

    @BindingAdapter("uriStringImage")
    public static void uriStringImage(ImageView imageView, String stringUri) {
        try {
            Uri uri = Uri.parse(stringUri);
            imageView.setImageURI(uri);
        } catch (Exception e) {
            e.fillInStackTrace();
            imageView.setImageResource(R.drawable.camera);
        }
    }
}
