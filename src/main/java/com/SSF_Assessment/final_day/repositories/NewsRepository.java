package com.SSF_Assessment.final_day.repositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.SSF_Assessment.final_day.models.News;

@Repository
public class NewsRepository {

    @Value("${crypto.cache.duration}")
    private Long cacheTime;

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    // String news atau News news
    public void save(String news, String payload) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(news, payload, Duration.ofSeconds(cacheTime));
    }

    // sama ngn bawah
    public Optional<String> get(String news) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(news);

        if (null == value)
            return Optional.empty(); // empty box
        return Optional.of(value); // box with data
    }

    public String getArticle(String id) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(id);
    }
}