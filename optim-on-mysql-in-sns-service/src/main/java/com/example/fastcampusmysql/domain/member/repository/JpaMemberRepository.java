package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository
//@Primary
public interface JpaMemberRepository
extends MemberRepository, JpaRepository<Member, Long>
{
    @PostConstruct
    default void init() throws Exception
    {
        System.out.println(">>> %s init()".formatted(this.getClass().getName()));
    }
    @PreDestroy
    default void destroy() throws Exception
    {
        System.out.println(">>> %s destroy()".formatted(this.getClass().getName()));
    }
}
