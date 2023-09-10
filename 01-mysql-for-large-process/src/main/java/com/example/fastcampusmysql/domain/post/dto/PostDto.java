package com.example.fastcampusmysql.domain.post.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PostDto(
        Long id,
        Long memberId,
        String contents,
        LocalDate createdDate,
        LocalDateTime createdAt,
        Long likeCount,
        Long version
) {
}
