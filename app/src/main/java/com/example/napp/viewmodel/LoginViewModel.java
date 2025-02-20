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
        String emailInput = getEmail().trim();  // Loại bỏ khoảng trắng trước và sau
        String passInput = getPass().trim();

        Log.d("Login", "Email: " + emailInput);

        // Khởi tạo đối tượng User và kiểm tra tính hợp lệ
        User user = new User(emailInput, passInput);

        if (!user.isValidPass()) {
            messageLogin.setValue("Mật khẩu phải có ít nhất 6 ký tự");
        } else if (!user.isValidEmail()) {
            messageLogin.setValue("Email không hợp lệ"+ emailInput);
        } else {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(emailInput,passInput).addOnCompleteListener(task -> {
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
        String emailInput = getEmail().trim();
        String passInput = getPass().trim();
        String pass2Input = getPass2().trim();

        User user = new User(emailInput,passInput);

        if(!user.isValidPass()){
            messageLogin.setValue("Mật khẩu phải có ít nhất 6 ký tự");
        } else if (!user.isValidEmail()) {
            messageLogin.setValue("Email không hợp lệ"+ emailInput);
        } else if(!passInput.equals(pass2Input)){
            messageLogin.setValue("Mật khẩu nhập lại không trùng khớp");
        } else {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(emailInput, passInput)
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



//    public void onClickRegister(){
//        navigateToHome.setValue(true);
//    }

}
