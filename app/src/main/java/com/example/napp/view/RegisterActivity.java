package com.example.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.napp.MainActivity;
import com.example.napp.R;
import com.example.napp.databinding.ActivityLoginBinding;
import com.example.napp.databinding.ActivityRegisterBinding;
import com.example.napp.viewmodel.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private LoginViewModel viewModel;

    private ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        observeIntent();
    }


    private void init(){
        //Khoi tao firebase
        mAuth = FirebaseAuth.getInstance();

        //Khoi tao databinding
        activityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);

        //khoi tao viewmodel
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //Gán viewmodel vào databinding
        activityRegisterBinding.setLoginViewModel(viewModel);
        activityRegisterBinding.setLifecycleOwner(this);
    }


    private void observeIntent(){
        // Quan sat chuyen huong
        viewModel.navigateToHome.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(Boolean.TRUE.equals(aBoolean)){
                    try {
                        Toast.makeText(RegisterActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    viewModel.navigateToHome.setValue(false);
                }
            }
        });
    }

}