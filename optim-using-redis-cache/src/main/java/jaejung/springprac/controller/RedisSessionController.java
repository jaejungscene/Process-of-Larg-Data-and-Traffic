package jaejung.springprac.controller;

import jaejung.springprac.service.RedisService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis")
public class RedisSessionController {
    private final RedisService redisService;
    private final String key = "sessionAccessKey";

    @GetMapping("/session/creation")
    public String createSession(HttpSession session, @RequestParam String name) {
        System.out.println("-----------------> controller 02");
        session.setAttribute(key, name);
        return "saved.";
    }

    @GetMapping("/session")
    public String getSession(HttpSession session) {
        System.out.println("-----------------> controller 01");
        String result = (String)session.getAttribute(key);
        return result;
    }




    @GetMapping("/set")
    public String setKeyValue(@RequestParam String name, @RequestParam String value) {
        redisService.setKeyValue(name, value);
        return "saved.";
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String name) {
        return redisService.getValueBy(name);
    }
}
