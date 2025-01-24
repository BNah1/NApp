package com.example.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.napp.MainActivity;
import com.example.napp.R;
import com.example.napp.databinding.ActivityLoginBinding;
import com.example.napp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        // sử dụng databinding
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        // khởi tạo viewmodel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Gán ViewModel vào DataBinding
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        //Function
        ClickRegister();

    }

    private void ClickRegister(){
        loginViewModel.navigateToHome.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(Boolean.TRUE.equals(aBoolean)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}