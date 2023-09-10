package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.CreatePostLikeUsecase;
import com.example.fastcampusmysql.application.usecase.CreatePostUsecase;
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
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUsecase getTimelinePostsUsecase;
    final private CreatePostUsecase createPostUsecase;
    final private CreatePostLikeUsecase createPostLikeUsecase;

    @GetMapping("test")
    public List<PostDto> getAll() {
        return postReadService.getAll();
    }

    @PostMapping("")
    public Long createPostAndDeliver(@RequestBody PostCommand command) {
        return createPostUsecase.execute(command);
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
    public Page<PostDto> getPosts(
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
    public PageCursor<PostDto> getPostsByCursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ){
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline-pull")
    public PageCursor<Post> getTimelineByPull(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ){
        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline-push")
    public PageCursor<Post> getTimelineByPush(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ){
        return getTimelinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/pessimistic-lock/like/pull")
    public void likePostPes(Long postId) {
        postWriteService.likePostPessimistic(postId);
    }

    @PostMapping("/{postId}/optimistic-lock/like/pull")
    public void likePostOpt(Long postId) {
        postWriteService.likePostOptimistic(postId);
    }

    @PostMapping("/{postId}/like/push")
    public void likePostV1(
            @PathVariable Long postId,
            @RequestParam Long memberId
    ) {
        createPostLikeUsecase.execute(postId, memberId);
    }
}
