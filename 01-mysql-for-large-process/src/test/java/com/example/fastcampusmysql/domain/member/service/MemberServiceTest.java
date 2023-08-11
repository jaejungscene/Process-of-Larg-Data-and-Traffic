package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

//@ExtendWith(MockitoExtension.class)
@Transactional
//@DataJpaTest
@DataJdbcTest
public class MemberServiceTest {
    @Autowired
    private MemberReadService memberReadService;
//    @InjectMocks
    @Autowired
    private MemberWriteService memberWriteService;

    @Test
    void getMemberTest() {
        var expected = MemberFixtureFactory.create();
        var registerMemberCommand = new RegisterMemberCommand(
                expected.getEmail(),
                expected.getNickname(),
                expected.getBirthday()
        );
        Member result = memberWriteService.registerMember(registerMemberCommand);
        Assertions.assertEquals(expected, result);

        MemberDto dto = memberReadService.getMember(result.getId());
        result = Member.builder()
                .id(dto.id())
                .nickname(dto.nickname())
                .email(dto.email())
                .birthday(dto.brithdaty())
                .build();
        Assertions.assertEquals(expected, result);
    }
}
