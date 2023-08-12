package com.example.fastcampusmysql.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@ToString
public class Post {
    private Long id;
    private Long memberId;
    private String contents;
    private LocalDate createdDate;
    private LocalDateTime createdAt;
    private Long likeCount;
    private Long version;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createdDate, LocalDateTime createdAt, Long likeCount, Long version) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.likeCount = likeCount == null ? 0 : likeCount;
        this.version = version;
    }

    public void increaseLikeCount() {
        likeCount += 1;
    }
}
