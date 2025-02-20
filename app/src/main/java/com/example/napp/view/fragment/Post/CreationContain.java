package com.example.napp.view.fragment.Post;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.napp.R;
import com.example.napp.constant.Constant;
import com.example.napp.databinding.FragmentCreationContainBinding;

public class CreationContain extends Fragment {
    private FragmentCreationContainBinding fragmentCreationContainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCreationContainBinding = FragmentCreationContainBinding.inflate(inflater,container,false);
        return fragmentCreationContainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentCreationContainBinding.creationPostImgGallery.setImageResource(R.drawable.icon_gallery);
        fragmentCreationContainBinding.creationPostAvatar.setImageResource(Constant.testUser.getProfilePicUrl());
    }
}