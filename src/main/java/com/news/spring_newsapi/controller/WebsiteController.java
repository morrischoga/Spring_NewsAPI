package com.news.spring_newsapi.controller;


import com.news.spring_newsapi.websites.HiphopDX;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebsiteController {

    @GetMapping("/hiphopdx")
    public String hiphopdx() throws IOException {



        return HiphopDX.getNews();
    }



}
