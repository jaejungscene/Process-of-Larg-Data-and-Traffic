package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.JdbcTemplateMemberRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberWriteService {
    final private MemberRepository memberRepository;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;



    @Transactional
    public Member registerMember(RegisterMemberCommand command) {
        return getMember(command);
    }

    @Transactional
    public Member getMember(RegisterMemberCommand command) {
        Member member = Member
                .builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

//        System.out.println("01--------------");
        Member savedMember = memberRepository.save(member);
//        System.out.println("02--------------"+savedMember);
//        var zero = 0/0;
        saveMemberNicknameHistory(savedMember);
//        System.out.println("03--------------");

        return savedMember;
    }


    public void changeNickname(Long memberId, String nickname){
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);


    }

    public void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }


    @PostConstruct
    public void init() throws Exception
    {
        System.out.println(">>> %s init()".formatted(this.getClass().getName()));
        System.out.println(">>> memberRepo: "+ this.memberRepository.getClass().getName());
    }
    @PreDestroy
    public void destroy() throws Exception
    {
        System.out.println(">>> %s destroy()".formatted(this.getClass().getName()));
    }
}
