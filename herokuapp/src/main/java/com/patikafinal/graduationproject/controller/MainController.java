package com.patikafinal.graduationproject.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(value="Main Page API Documents")
public class MainController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "welcome";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/newindex")
    public String homepage(){
        return "newindex";
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
}
