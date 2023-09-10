package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMemberUsecase {
    final private MemberReadService memberReadService;
    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        /**
         * get following members of memberId
         */
        var idsOfFollowings = followReadService.getFollowings(memberId)
                .stream()
                .map(FollowDto::toMemberId)
                .toList();
        return memberReadService.getMembersOf(idsOfFollowings);
    }
}
