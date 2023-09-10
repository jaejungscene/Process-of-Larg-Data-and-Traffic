package jaejung.springprac.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    @Cacheable(cacheNames = "testCache")
    public String getValueBy(String name){
        try {
            Thread.sleep(1000);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>. stop");
        } catch (Exception e) {
        }
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String fruitName = ops.get(name);
        return fruitName;
    }


    public void setKeyValue(String name, String value){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(name, value, 60, TimeUnit.SECONDS);
    }
}
