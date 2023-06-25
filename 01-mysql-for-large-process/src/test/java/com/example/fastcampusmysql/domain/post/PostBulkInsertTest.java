package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.PostFixtureFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() throws InterruptedException {
        var easyRandom = PostFixtureFactory.get(
                3L,
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1)
        );

        long start = System.currentTimeMillis();
        var posts = IntStream.range(0, 10000 * 10)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        long finish = System.currentTimeMillis();
        System.out.println(">>>>>>> Creating Class Time: " + (finish - start)/1000.0 + " s");

        start = System.currentTimeMillis();
        postRepository.bulkInsert(posts);
        finish = System.currentTimeMillis();
        System.out.println(">>>>>>> SQL query Time: " + (finish - start)/1000.0 + " s");
    }
}
