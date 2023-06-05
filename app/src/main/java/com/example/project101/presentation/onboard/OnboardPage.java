package com.example.project101.presentation.onboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.project101.databinding.ActivityBoardBinding;
import com.example.project101.util.Constant;

public class OnboardPage extends AppCompatActivity {

    private ActivityBoardBinding binding;
    private OnboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializer();
        setContentView(binding.getRoot());
    }

    private void initializer() {
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(OnboardViewModel.class);
        binding.setBoardViewModel(viewModel);
        binding.userImage.setOnClickListener(v -> checkPermission());
    }

    private void checkPermission() {
        String permission;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, Constant.GALLERY_PERMISSION_REQUEST_CODE);
        } else {
            // Permission is already granted, proceed with accessing the gallery
            openGallery();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.GALLERY_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                checkPermission();
                Toast.makeText(this, "Gallery permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openGallery() {
        galleryLauncher.launch("image/*");
    }
    private final ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    // Process the selected image URI
                    viewModel.details.setUriString(String.valueOf(uri));
                    binding.userImage.setImageURI(uri);
                }
            }
    );
}