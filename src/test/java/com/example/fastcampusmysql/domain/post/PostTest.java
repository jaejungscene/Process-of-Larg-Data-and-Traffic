package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class PostTest {
    @BeforeEach
    public void startEach(TestInfo info) {
        System.out.println("#########################################");
        System.out.println(String.format(">>>> START %s", info.getTestMethod().get().getName()));
    }

    @AfterEach
    public void endEach(TestInfo info) {
        System.out.println(String.format(">>>> END %s", info.getTestMethod().get().getName()));
        System.out.println("#########################################");
    }

    @Test
    public void testPostInstance() {
        Post post01 = Post
                .builder()
                .id(1L)
                .memberId(312L)
                .contents("contents")
                .build();
        System.out.println(Post.class);
        System.out.println("> "+ post01.getId());
        System.out.println("> "+ post01.getMemberId());
        System.out.println("> "+ post01.getContents());
        System.out.println("> "+ post01.getCreateDate());
        System.out.println("> "+ post01.getCreateAt());
    }

    @Test
    public void test01() {
        System.out.println("hello world");
    }
}
