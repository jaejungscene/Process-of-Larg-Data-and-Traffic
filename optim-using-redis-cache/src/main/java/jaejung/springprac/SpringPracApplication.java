package jaejung.springprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringPracApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringPracApplication.class, args);
	}
}
