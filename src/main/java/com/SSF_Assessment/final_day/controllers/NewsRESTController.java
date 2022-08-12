package com.SSF_Assessment.final_day.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SSF_Assessment.final_day.models.News;
import com.SSF_Assessment.final_day.services.NewsService;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/news", produces=MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {

    @Autowired
    private NewsService nwsSvc;

    @GetMapping("{id}")
    public ResponseEntity<String> getArticle(@PathVariable("id") String id) {
        Optional<News> opt = nwsSvc.getSavedArticles(id); 

        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                    .add("error", "Cannot find news article %s".formatted(id))
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.toString());
        }
        News news = opt.get();
        return ResponseEntity.ok(news.toJson().toString());

    }
}