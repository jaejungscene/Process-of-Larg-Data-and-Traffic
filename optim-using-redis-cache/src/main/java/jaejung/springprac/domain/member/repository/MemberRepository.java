package jaejung.springprac.domain.member.repository;

import java.util.List;
import java.util.Optional;

import jaejung.springprac.domain.member.Member;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

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
