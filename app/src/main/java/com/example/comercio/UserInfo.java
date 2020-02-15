package com.example.comercio;

public class UserInfo {

    private String email;
    private String name;
    private String register_number;

    public UserInfo() {

    }

    public UserInfo(String email, String name, String register_number) {
        this.email = email;
        this.name = name;
        this.register_number = register_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegister_number() {
        return register_number;
    }

    public void setRegister_number(String register_number) {
        this.register_number = register_number;
    }
}
