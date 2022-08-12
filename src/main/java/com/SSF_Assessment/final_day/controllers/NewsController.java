package com.SSF_Assessment.final_day.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SSF_Assessment.final_day.models.News;
import com.SSF_Assessment.final_day.services.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping(produces = { "text/html" })
    public String getArtciles(Model model, @RequestParam String news) {

        List<News> articles = newsSvc.getArticles(news);
        model.addAttribute("articles", articles);
        model.addAttribute("news", news);
        return "index";
    }

    // @PostMapping
    

}
