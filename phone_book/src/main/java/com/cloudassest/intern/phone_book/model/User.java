package com.cloudassest.intern.phone_book.model;

import org.springframework.data.annotation.Id;

public class User {
    public User(){

    }
    public User(String userName,String phoneNum){
        this.userName=userName;
        this.phoneNum=phoneNum;
    }
    @Id
    private String id;
    private String userName;
    private String phoneNum;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
