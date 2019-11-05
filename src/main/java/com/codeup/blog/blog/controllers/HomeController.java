package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//
//1. Create a HomeController class. This class should have one method with a GetMapping for /.
//        It should return a string that says "This is the landing page!".


@Controller
public class HomeController {
//
//    version 1
//    @GetMapping("/")
//    @ResponseBody
//    public String index(){
//        return "This is the landing page!";
//    }


//    version 2
    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "This is the landing page!";
    }

}
