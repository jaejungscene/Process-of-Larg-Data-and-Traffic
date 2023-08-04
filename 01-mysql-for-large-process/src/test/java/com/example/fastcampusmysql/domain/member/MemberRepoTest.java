package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.domain.member.repository.JdbcTemplateMemberRepository;
import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class MemberRepoTest {
    @Autowired
    JdbcTemplateMemberRepository memberRepository;

    @Test
//    @Transactional
    public void testSave(){
        Random rand = new Random();
        var seed = rand.nextLong(100000L);
        var member = MemberFixtureFactory.create(seed);
        var newMember = memberRepository.save(member);
        System.out.println(member);
        System.out.println(newMember);

        Assertions.assertEquals(
                member.getNickname(),
                newMember.getNickname()
        );
    }
}
