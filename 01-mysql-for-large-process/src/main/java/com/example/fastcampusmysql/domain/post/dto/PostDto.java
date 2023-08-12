package com.example.fastcampusmysql.domain.post.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PostDto(
        Long id,
        String contents,
        LocalDateTime createdAt,
        Long likeCount
) {
}
