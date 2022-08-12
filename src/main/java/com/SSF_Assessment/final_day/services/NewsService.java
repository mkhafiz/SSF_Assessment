package com.SSF_Assessment.final_day.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.SSF_Assessment.final_day.models.News;
import com.SSF_Assessment.final_day.repositories.NewsRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class NewsService {

    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/";

    @Autowired
    private NewsRepository newsRepo;

    public List<News> getArticles(String news) { 

        Optional<String> opt = newsRepo.get(news);
        String payload;

        if (opt.isEmpty()) {

            System.out.println("Getting news from Server");

            try {

                // Create the GET request, GET url
                RequestEntity<Void> req = RequestEntity.get(URL).build();
                // Make the call to Server
                RestTemplate template = new RestTemplate();
                ResponseEntity<String> resp;
                // Throws an exception if status code not in between 200 - 399
                resp = template.exchange(req, String.class);

                // Get the payload and do something with it
                payload = resp.getBody();
                System.out.println("payload: " + payload);

                newsRepo.save(news, payload);
            } catch (Exception ex) {
                System.err.printf("Error: %s\n", ex.getMessage());
                return Collections.emptyList();
            }
        } else {
            // Retrieve the value for the box
            payload = opt.get();
            System.out.printf(">>>> cache: %s\n", payload);
        }

        // Convert payload to JsonObject
        // Convert the String to a Reader
        Reader strReader = new StringReader(payload);
        // Create a JsonReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);
        // Read the payload as Json object
        JsonObject newsResult = jsonReader.readObject();
        JsonArray result = newsResult.getJsonArray("index");
        List<News> list = new LinkedList<>();

        return list;
    }

    public Optional<News> getSavedArticles(String id) {
        String result = newsRepo.getArticle(id);
        if (null == result)
            return Optional.empty();

        return Optional.of(News.create(result));
    }

    public List<News> saveArticles() {
        

        return null;
    }
}