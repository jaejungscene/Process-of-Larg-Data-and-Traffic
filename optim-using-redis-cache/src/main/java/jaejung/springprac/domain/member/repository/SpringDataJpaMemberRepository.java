package jaejung.springprac.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jaejung.springprac.domain.member.Member;

//@Repository
public interface SpringDataJpaMemberRepository 
extends JpaRepository<Member, Long>, MemberRepository {
    //    Optional<Member> findByName(String name);

}
