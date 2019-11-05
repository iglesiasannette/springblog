package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController{


//        GET	/posts	posts index page
//        GET	/posts/{id}	view an individual post
//        GET	/posts/create	view the form for creating a post
//        POST	/posts/create	create a new post



    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "Posts Index Page";
    }

//    @GetMapping("/posts")
//    @ResponseBody
//    public String index(){
//        return "Posts Index Page";
//    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postById(@PathVariable long id){
        return "This post's id is " + id;

        //common method name for this function is "show"
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String showCreateForm(){
        return "this page allows the user to view the form to create a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestParam String title, @RequestParam String body){
        System.out.println("title = " + title);
        System.out.println("body = " + body);

        return "You've created a new post!";
    }




}
