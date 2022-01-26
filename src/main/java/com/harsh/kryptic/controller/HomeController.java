package com.harsh.kryptic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class HomeController {

    @GetMapping
    public String displayHomePage(){
        return "Welcome to Kryptic - a simple crypto currency price and portfolio tracker";
    }

}
