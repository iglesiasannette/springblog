package com.codeup.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//2.This controller should listen for requests for several routes that correspond to basic
//        arithmetic operations and produce the result of the arithmetic.
//
//        url	               response
//        /add/3/and/4	           7
//        /subtract/3/from/10	   7
//        /multiply/4/and/5	      20
//        /divide/6/by/3	       2

@Controller
public class MathController {




    @RequestMapping(path = "/add/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable long num1, @PathVariable long num2){
//        this return parses the long datatype into a string
//        long total = num1 + num2;
//        return String.valueOf(total);

        //This return displays the whole result as a string
        return num1 + " plus " + num2 + " is " + (num1 + num2) + "!";
    }

    @RequestMapping(path= "/subtract/{num1}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable long num1, @PathVariable long num2){
        return num1 + " subtracted from " + num2 + " equals " + (num2-num1);
    }


    @RequestMapping(path = "/multiply/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable long num1, @PathVariable long num2){
        return num1 + " times " + num2 + " equals to " + (num1 * num2);

    }

    @RequestMapping(path = "/divide/{num1}/by/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable double num1, @PathVariable double num2){
        if(num2 == 0){
            return num1 + " can not be divided by 0.";
        }
        return num1 + " divided by " + num2 + " equals " + (num1/num2);

        //we used double here to account for decimals
    }


}
