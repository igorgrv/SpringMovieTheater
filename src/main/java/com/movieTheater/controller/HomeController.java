package com.movieTheater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author igorg
 *
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
