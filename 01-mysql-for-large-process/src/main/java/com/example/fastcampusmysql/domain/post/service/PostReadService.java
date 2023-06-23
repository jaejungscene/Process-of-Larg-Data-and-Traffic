package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(this::toPostDto)
                .toList();
    }

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /**
         * return List -> [작성일자, 작성회원, 작성 게시물 갯수]
         *
         */
        return postRepository.groupByCreatedDateOnMember(request);
    }
}
