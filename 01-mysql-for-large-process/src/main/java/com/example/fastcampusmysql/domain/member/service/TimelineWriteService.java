package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.repository.TimelineRepository;
import com.example.fastcampusmysql.domain.post.entity.Timeline;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {
    final private TimelineRepository timelineRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberIds){
        var timelines =toMemberIds.stream()
                .map((memberId) -> Timeline.builder().postId(postId).build())
                .toList();

        timelineRepository.bulkInsert(timelines);
    }
}
