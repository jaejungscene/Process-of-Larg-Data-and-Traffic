package com.example.fastcampusmysql.domain.post.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "memberId")
    private Long memberId;

    private String contents;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "likeCount")
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
