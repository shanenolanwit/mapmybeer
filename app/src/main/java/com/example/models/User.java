package com.example.models;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class User {

    private String email;
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try{
            byte[] data = password.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            this.password = base64;
        } catch(UnsupportedEncodingException e){

        }

    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                '}';
    }
}
