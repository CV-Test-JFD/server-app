package com.cvt.user.model;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)
    private String name;

    @Column(nullable=false, length=255)
    private String email;

    @Column(nullable=false, length=255)
    private String password;


    public User(){
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name= name;
        this.password = password;
        this.email= email;
    }

    public User(String name, String email, String password) {
        this.name= name;
        this.password = password;
        this.email= email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
