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
import java.util.List;
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
                4L,
                LocalDate.of(1800, 1, 1),
                LocalDate.of(2023, 6, 1)
        );

        int numClass = 10000;
        start = System.currentTimeMillis();
        List<Post> posts = IntStream.range(0, 20 * numClass)
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

    @Test
    public void easyRandomTest() throws InterruptedException {
        Long start, finish;
        var easyRandom = PostFixtureFactory.get(
                1L,
                4L,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2023, 6, 1)
        );
        Post post = postRepository.save(easyRandom.nextObject(Post.class));
        System.out.println(post);
    }
}
