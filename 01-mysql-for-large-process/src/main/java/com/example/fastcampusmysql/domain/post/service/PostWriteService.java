package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    final private PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
        /**
         * from, to 회원 정보를 받아서 저장할텐데..
         * from <-> to validate
         */
    }

    @Transactional
    public void likePost(Long postId){
        var post = postRepository.findById(postId, false).orElseThrow();
        post.increaseLikeCount();
        postRepository.save(post);
    }
}
