package com.codeup.blog.models;
import javax.persistence.*;


//Create a Post class. This class will ultimately represent a POST record from our database.
// The class should have private properties and getters and setters for a title and body.

import com.codeup.blog.Repositories.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name="posts")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Post (long id, String title, String body, User user){
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;

    }


    //this constructor is for the post/create form and saves the title and body the user types in
    public Post (String title, String body){
        this.title = title;
        this.body = body;

    }

    public Post (){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
