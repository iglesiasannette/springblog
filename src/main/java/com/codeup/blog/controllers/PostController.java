package com.codeup.blog.controllers;

import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.services.EmailService;
import com.codeup.blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PostController{



    private final PostRepository postDao;
    private final UserRepository userDao;


    //dependency injection
    @Autowired
    EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model model, @RequestParam(defaultValue = "") String search) {
        model.addAttribute("posts", postDao.findByTitleContaining(search));

        return "posts/index";
    }


    //this method renders the view and creates a new Post
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String showCreateForm(Model vModel){
        vModel.addAttribute("post", new Post());

        return "posts/create";
    }

    //1. this method is getting the parameters from the form title and body and posting it
    //to the post/create form.
    //2. A new Post object named postToBeCreated is rendered on the page and the parameters
    //the user entered are being passed and saved into the new post. (note: a new Post
    //constructor in the Post class was made as to be able to create a Post object that just took in the title
    //and body variables.)
    //3. the setUser method needs to reference the userDao to assign the User to the specific post.
    //(note: a UserRepository was needed to be made to be able to link the post_id and user_id
    //4. the postDao needs to be referenced as to save the info of the post.
    //5. the user is redirected to /posts
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Post postToBeCreated){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeCreated.setUser(user);
        Post savedPost = postDao.save(postToBeCreated);
        emailService.prepareAndSend(savedPost, "post created", "A new post has been created with the id of " +
                 + savedPost.getId());
        return "redirect:/posts";
    }


//this method allows a post title to be clicked from the index page
// and will go to /posts/{id} (the url with the post id) and display or "show" the individual post
    //the post id's autoincrement and the url will contain the individual post id
    //the id and Model object are passed so the the addAttribute method can be used.
    //the postDao needs to be referenced as to retrieve the post id
    //then display everything to do with the post on the posts/show html

@GetMapping("/posts/{id}")
public String show(@PathVariable long id, Model viewModel){
    System.out.println(postDao.getOne(id).getUser().getEmail());
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
    public String update(@PathVariable long id, @ModelAttribute Post updatedPost) {
        updatedPost.setUser(userDao.getOne(1L));
        postDao.save(updatedPost);
        return "redirect:/posts/" + id;
    }


    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}





