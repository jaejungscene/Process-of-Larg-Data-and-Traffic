package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    final private FollowRepository followRepository;

    public FollowDto toDto(Follow follow) {
        return new FollowDto(follow.getId(), follow.getFromMemberId(), follow.getToMemberId(), follow.getCreatedAt());
    }

    public List<FollowDto> getFollowingsOf(Long memberId){
        return followRepository.findAllByFromMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<Follow> getFollowers(Long memberId) {
        return followRepository.findAllByToMemberId(memberId);
    }

    public List<FollowDto> getAllFollow() {
        return followRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
}
