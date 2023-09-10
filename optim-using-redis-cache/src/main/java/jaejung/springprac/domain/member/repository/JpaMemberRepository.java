package jaejung.springprac.domain.member.repository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import jaejung.springprac.domain.member.Member;

// @Repository
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

//    @PostConstruct
//    public void init() throws Exception
//    {
//        System.out.println(">>> %s init()".formatted(this.getClass().getName()));
//    }
//    @PreDestroy
//    public void destroy() throws Exception
//    {
//        System.out.println(">>> %s destroy()".formatted(this.getClass().getName()));
//    }
}
