package com.codeup.blog.controllers;

import com.codeup.blog.Repositories.PostRepository;
import com.codeup.blog.Repositories.UserRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


@Controller
public class PostController{



    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }


    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String showCreateForm(){

        return "posts/create";
    }

    //1. this method is requesting the parameters from the form title and body and posting it
    //to the post/create form.
    //2. A new Post object named post is being made and the parameters
    //the user entered are being passed into the new post as variables. (note: a new Post
    //constructor in the Post class was made as to be able to create a Post object that just took in the title
    //and body variables.)
    //3. the setUser method needs to reference the userDao to assign the User to the specific post.
    //(note: a UserRepository was needed to be made to be able to link the post_id and user_id
    //4. the postDao needs to be referenced as to save the info of the post.
    //5. the user is redirected to their own post
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String create(@RequestParam String title, @RequestParam String body){
        Post post = new Post(title,body);
        post.setUser(userDao.getOne(1L));
        postDao.save(post);
        return "redirect:/posts/1";
    }


//this method allows a post title to be clicked from the index page
// and will go to /posts/{id} (the url with the post id) and display or "show" the individual post
    //the post id's autoincrement and the url will contain the individual post id
    //the id and Model object are passed so the the addAttribute method can be used.
    //the postDao needs to be referenced as to retrieve the post id
    //then display everything to do with the post on the posts/show html

@GetMapping("/posts/{id}")
public String show(@PathVariable long id, Model viewModel){
    viewModel.addAttribute("post", postDao.getOne(id));
    return "posts/show";
}

//when the user clicks "edit" on the posts/show html, they are directed to the  "/posts/{id}/edit" url
    //in the edit method, the path variable and the model object are passed
    //the addAttribute method is used to assign the old title and body (via the post id)
    // to the view of the edit page,
    // then comes the update method...
    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }


    //when the changes are made, this page takes in the title and body and sets the changes to the
    //post via it's unique id with the variable name oldPost. That way, when the user visits that particular
    //post's edit page, the form is pre-populated with the old data.
    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setBody(body);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }


    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}





