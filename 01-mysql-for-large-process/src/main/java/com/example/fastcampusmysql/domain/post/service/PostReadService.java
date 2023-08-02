package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;

    public PostDto toPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getMemberId(),
                post.getContents(),
                post.getCreatedDate(),
                post.getCreatedAt()
        );
    }

    @Transactional
    public List<PostDto> getAll() {
        return postRepository.findAllWithLimit()
                .stream()
                .map(this::toPostDto)
                .toList();
    }

    @Transactional
    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /**
         * return List -> [작성일자, 작성회원, 작성 게시물 갯수]
         */
        return postRepository.groupByCreatedDateOnMember(request);
    }

    @Transactional
    public Page<Post> getPosts(Long memberId, Pageable pageable){
        return postRepository.findAllByMemberIdForPagination(memberId, pageable);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberId, cursorRequest);
        Long nextKey = posts
                .stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByMemberIdForCursorBasedPaginationByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        } else{
            return postRepository.findAllByMemberIdForCursorBasedPagination(memberId, cursorRequest.size());
        }
    }
}
