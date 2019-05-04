package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAuthResult {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("newUser")
    @Expose
    private boolean newUser;

    @SerializedName("authenticated")
    @Expose
    private boolean authenticated;

    public UserAuthResult() {
    }

    public UserAuthResult(String email, boolean newUser, boolean authenticated) {
        this.email = email;
        this.newUser = newUser;
        this.authenticated = authenticated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }


    @Override
    public String toString() {
        return "UserAuthResult{" +
                "email='" + email + '\'' +
                ", newUser=" + newUser +
                ", authenticated=" + authenticated +
                '}';
    }
}
