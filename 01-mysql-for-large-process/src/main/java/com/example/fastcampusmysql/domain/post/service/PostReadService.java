package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostLikeRepository;
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
    final private PostLikeRepository postLikeRepository;

    private PostDto toPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .contents(post.getContents())
                .createdAt(post.getCreatedAt())
                .likeCount(
                        postLikeRepository.count(post.getId())
                )
                .build();
    }

    private static long getNextKey(List<Post> posts) {
        return posts
                .stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
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
        return postRepository.groupByCreatedDateOnMember(request);
    }

    @Transactional
    public Post getPosts(Long postId){
        return postRepository.findById(postId, false).orElseThrow();
    }

    @Transactional
    /**
     * for Offset Pagination
     */
    public Page<PostDto> getPosts(Long memberId, Pageable pageable){
        return postRepository.findAllByMemberIdForPagination(memberId, pageable)
                .map(this::toPostDto);
    }

    @Transactional
    /**
     * for Cursor Pagination
     */
    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberId, cursorRequest);
        Long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    @Transactional
    /**
     * for Pull model
     */
    public PageCursor<Post> getPosts(List<Long> memberIds, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberIds, cursorRequest);
        Long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    @Transactional
    /**
     * for Push model
     */
    public List<Post> getPosts(List<Long> postIds) {
        return postRepository.findAllByInId(postIds);
    }

    @Transactional
    public List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdAndOrderBYIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        } else{
            return postRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
        }
    }

    @Transactional
    public List<Post> findAllBy(List<Long> memberIds, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndInMemberIdAndOrderBYIdDesc(cursorRequest.key(), memberIds, cursorRequest.size());
        } else{
            return postRepository.findAllByInMemberIdAndOrderByIdDesc(memberIds, cursorRequest.size());
        }
    }

}
