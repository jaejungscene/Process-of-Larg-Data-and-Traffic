package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostContoller {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;

    @GetMapping("")
    public List<PostDto> getAll() {
        return postReadService.getAll();
    }

    @PostMapping("")
    public Long create(PostCommand command) {
        return postWriteService.create(command);
    }
}
