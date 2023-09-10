package jaejung.springprac.repository;


public class MemoryMemberRepositoryTest {
    // MemoryMemberRepository repository = new MemoryMemberRepository();


    // /**
    //  * if you delete this method,
    //  * error occurs because each Test method share repository.
    //  */
    // @AfterEach
    // public void afterEach(){
    //     repository.clearStore();
    // }

    // @Test
    // public void save(){
    //     Member member = new Member();
    //     member.setName("spring");

    //     repository.save(member);
    //     Member result = repository.findById(member.getId()).get();
    //     Assertions.assertThat(member).isEqualTo(result);
    // }

    // @Test
    // public void findByName(){
    //     Member member1 = new Member();
    //     member1.setName("spring1");
    //     repository.save(member1);

    //     Member member2 = new Member();
    //     member2.setName("spring2");
    //     repository.save(member2);

    //     Member result = repository.findByName("spring1").get();
    //     Assertions.assertThat(result).isEqualTo(member1);
    // }

    // @Test
    // public void findAll(){
    //     int totalnum = 2;
    //     Member[] memberArr = new Member[totalnum];
    //     Member member;
    //     for(int i=0; i<totalnum; i++){
    //         member = new Member();
    //         member.setName(String.format("spring%d", i));
    //         repository.save(member);
    //     }

    //     List<Member> result = repository.findAll();
    //     Assertions.assertThat(result.size()).isEqualTo(totalnum);
    // }
}
