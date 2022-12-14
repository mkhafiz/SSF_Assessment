package com.SSF_Assessment.final_day.repositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {

    @Value("${crypto.cache.duration}")
    private Long cacheTime;

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public void save(String id, String payload) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(id, payload, Duration.ofSeconds(cacheTime));
    }

    public Optional<String> get(String news) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(news);

        if (null == value)
            return Optional.empty(); 
        return Optional.of(value); 
    }

    public String getArticle(String id) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(id);
    }
}