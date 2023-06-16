package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;
    public Member register(RegisterMemberCommand command) {
        /**
         * Desc:
         *      register user's info(e-mail, nickname, birth)
         *      nickname don't exceed 10 letters.
         *
         * parameters:
         *      memberRegisterCommand
         *
         * memberRepository.save(member)
         */

        var member = Member
                .builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        return memberRepository.save(member);
    }
}
