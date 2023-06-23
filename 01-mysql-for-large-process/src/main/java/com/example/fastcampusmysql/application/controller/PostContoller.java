package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Long create(@RequestBody PostCommand command) {
        return postWriteService.create(command);
    }

    @PostMapping("/member")
    public List<DailyPostCount> getDailyPostCount(@RequestBody DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }
}
