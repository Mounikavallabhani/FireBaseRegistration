package com.example.mansopresk05.registration;

/**
 * Created by Mansopresk05 on 11/16/2017.
 */

public class RegisterValues {

    String name;
    String mobile;
    String mail;
    String password;


    public RegisterValues(String name, String mobile, String mail, String password) {
        this.name = name;
        this.mobile = mobile;
        this.mail = mail;
        this.password = password;
    }

    public RegisterValues() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

