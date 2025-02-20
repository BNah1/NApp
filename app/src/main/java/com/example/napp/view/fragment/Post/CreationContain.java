package com.example.napp.view.fragment.Post;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.napp.R;
import com.example.napp.constant.Constant;
import com.example.napp.databinding.FragmentCreationContainBinding;
import com.example.napp.viewmodel.PostViewModel;

public class CreationContain extends Fragment {
    PostViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout cho Fragment CreationContain
        FragmentCreationContainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_creation_contain, container, false);

        // Lấy ViewModel từ Activity (sử dụng cùng một ViewModel đã được tạo trong Fragment cha)
        viewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        // Gán ViewModel vào DataBinding cho fragment_creation_contain
        binding.setPostViewModel(viewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.creationPostImgGallery.setImageResource(R.drawable.icon_gallery);
        binding.creationPostAvatar.setImageResource(Constant.testUser.getProfilePicUrl());

        // Trả về root của fragment_creation_contain
        return binding.getRoot();
    }

}