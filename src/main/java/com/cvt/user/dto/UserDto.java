package com.cvt.user.dto;

import com.cvt.user.model.User;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    public UserDto(){
    }
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email=  user.getEmail();
        this.password=user.getPassword();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
