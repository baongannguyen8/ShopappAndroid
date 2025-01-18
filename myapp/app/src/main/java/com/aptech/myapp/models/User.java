package com.aptech.myapp.models;

public class User {
    private int id;
    private String email;
    private String name;
    private int role;
    private String avatar;
    private String phone;

    public User(int id, String email, String name, int role, String avatar, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.avatar = avatar;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPhone() {
        return phone;
    }
}