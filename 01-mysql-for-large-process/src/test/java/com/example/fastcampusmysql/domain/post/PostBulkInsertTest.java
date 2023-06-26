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
        Long start, finish;
        var easyRandom = PostFixtureFactory.get(
                1L,
                3L,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2023, 6, 1)
        );

        start = System.currentTimeMillis();
        var posts = IntStream.range(0, 200000)
//        IntStream.range(0, 10)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
//                .forEach((x)->{System.out.println(x.getId()+"/"+x.getMemberId()+"/"+x.getCreatedDate()+"/"+x.getCreatedAt());});
                .toList();
        finish = System.currentTimeMillis();
        System.out.println("-------------->>>> Creating Class Time: " + (finish - start)/1000.0 + " s");

        start = System.currentTimeMillis();
        postRepository.bulkInsert(posts);
        finish = System.currentTimeMillis();
        System.out.println("-------------->>>> SQL query Time: " + (finish - start)/1000.0 + " s");
    }
}
