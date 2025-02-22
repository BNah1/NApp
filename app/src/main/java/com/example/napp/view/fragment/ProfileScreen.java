package com.example.napp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.napp.MainActivity;
import com.example.napp.R;
import com.example.napp.databinding.FragmentProfileScreenBinding;
import com.example.napp.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileScreen extends Fragment {

    private FragmentProfileScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileScreenBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        binding.profileScreenIconLogout.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Toast.makeText(requireContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}