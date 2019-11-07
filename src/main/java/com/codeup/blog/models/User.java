package com.codeup.blog.models;

//Create a User class, with (at least) fields for id, username, email, and password.

import javax.persistence.*;

@Entity
@Table(name="users")


public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;


    @Column(nullable = false, length = 100)
    private String password;


    public User (long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }

    protected User (){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return email;
    }

    public void setTitle(String title) {
        this.email = title;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
