package com.example.napp.viewmodel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.napp.data.model.User;
import com.google.firebase.auth.FirebaseAuth;


public class LoginViewModel extends ViewModel {

    private String email;
    private String pass;

    public MutableLiveData<String> messageLogin = new MutableLiveData<>();
    public MutableLiveData<Boolean> navigateToHome = new MutableLiveData<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void onClickRegister(){
        navigateToHome.setValue(true);
    }

}
