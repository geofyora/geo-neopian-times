package com.geo.neopian.times.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @GetMapping("/")
    public String index() {
        return "Welcome to my Neopian Times retriever service.";
    }
}
