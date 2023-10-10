package com.order.management.request;

import com.google.gson.Gson;

public class LoginRequest {

    //fields / data members / attributes
    private String userName;

    private String password;

    //setters
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    //getters

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    //To String
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

}
