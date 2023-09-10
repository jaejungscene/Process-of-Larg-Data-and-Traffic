package jaejung.springprac.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 각각의 Test가 끝나면 rollback 데이터가 DB에 반영되지 않도록 해준다.
public class MemberServiceIntegrationTest {
    // @Autowired MemberService memberService;
    // @Autowired MemberRepository memberRepository;

    // @Test
    // void join() {
    //     Member member = new Member();
    //     member.setName("spring");

    //     Long saveId = memberService.join(member);

    //     Member findMember = memberService.findOne(saveId).orElseThrow();
    //     Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    // }

    // @Test
    // public void duplicateMember(){
    //     Member member1 = new Member();
    //     member1.setName("spring");
    //     Member member2 = new Member();
    //     member2.setName("spring");

    //     memberService.join(member1);
    //     IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    //     Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    // }
}
