package com.example.napp.viewmodel;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.napp.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginViewModel extends ViewModel {

    private String email;
    private String pass;
    private String pass2;
    public MutableLiveData<String> messageLogin = new MutableLiveData<>();
    public MutableLiveData<Boolean> navigateToHome = new MutableLiveData<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LiveData<String> getMessageLogin() {
        return messageLogin;
    }

    public LiveData<Boolean> getNavigateToHome() {
        return navigateToHome;
    }

    public void onClickLogin(){
        if (checkInput(email,pass,null)){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email.trim(),pass.trim()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    messageLogin.setValue("Đăng nhập thành công");
                    navigateToHome.setValue(true);
                } else {
                    messageLogin.setValue("Đăng nhập that bai");
                    navigateToHome.setValue(false);
                }
            });
        }
    }

    public void onClickSignUp(){
        if (checkInput(email,pass,pass2)){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email.trim(), pass.trim())
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            messageLogin.setValue("Đăng nhập thành công");
                            FirebaseUser userL = mAuth.getCurrentUser();
                            navigateToHome.setValue(true);
                        } else {
                            messageLogin.setValue("Đăng nhập that bai");
                            navigateToHome.setValue(false);
                        }
                    });
        }
    }


    private boolean checkInput( String email, String pass, String pass2) {
        if (email == null || pass == null || (pass2 != null && !Objects.equals(pass2, pass))){
            messageLogin.setValue("Vui lòng nhập đủ thông tin");
            return false;
        }

        // Khởi tạo đối tượng User và kiểm tra tính hợp lệ
        User user = new User(email.trim(), pass.trim());

        if (!user.isValidPass()) {
            messageLogin.setValue("Mật khẩu phải có ít nhất 6 ký tự");
            return false;
        } else if (!user.isValidEmail()) {
            messageLogin.setValue("Email không hợp lệ" + email);
            return false;
        }
        return true;
    }

}
