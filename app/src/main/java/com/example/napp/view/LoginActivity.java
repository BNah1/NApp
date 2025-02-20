package com.example.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.napp.MainActivity;
import com.example.napp.R;
import com.example.napp.databinding.ActivityLoginBinding;
import com.example.napp.viewmodel.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        observeIntent();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // kiem tra user dang nhap
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void init(){
        //khoi tao firebase
        mAuth = FirebaseAuth.getInstance();

        // sử dụng databinding
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        // khởi tạo viewmodel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Gán ViewModel vào DataBinding
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        activityLoginBinding.loginTxtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void observeIntent(){
        // Quan sat chuyen huong
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