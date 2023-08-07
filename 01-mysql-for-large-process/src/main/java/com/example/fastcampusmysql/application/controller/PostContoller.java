package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.GetTimelinePostsUsecase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostContoller {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUsecase getTimelinePostsUsecase;

    @GetMapping("test")
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

    @GetMapping("/member/{memberId}/offset")
    /**
     * Demo request
     * - http://localhost:8080/posts/member/1/offset?page=1&size=3&sort=createdDate,desc&sort=id,asc
     */
    public Page<Post> getPosts(
            @PathVariable Long memberId,
            Pageable pageable
    ) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/member/{memberId}/cursor")
    /**
     * Demo request
     * - first: http://localhost:8080/posts/member/1/cursor?size=3
     * - others: http://localhost:8080/posts/member/1/cursor?key=3243000&size=3
     */
    public PageCursor<Post> getPostsByCursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ){
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ){
        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
    }
}
