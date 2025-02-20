package com.example.napp.data.model;

public class UserProfile {
    final String fullName;
    final String email;
    final String password;
    final int profilePicUrl;
    final String uid;

    public UserProfile(String fullName, String email, String password, int profilePicUrl, String uid) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.profilePicUrl = profilePicUrl;
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getUid() {
        return uid;
    }
}
