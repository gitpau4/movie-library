package com.paula.movielibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping
    public String test() {
        return "Backend running for movie library!!";
    }
}
