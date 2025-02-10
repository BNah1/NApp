package com.example.napp.data.model;

import android.text.TextUtils;
import android.util.Patterns;

public class User {
    private String email;
    private String pass;

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
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

    public boolean isValidEmail(){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPass(){
        return !TextUtils.isEmpty(pass);
    }

}
