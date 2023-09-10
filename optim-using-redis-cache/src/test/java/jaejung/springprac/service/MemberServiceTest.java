package jaejung.springprac.service;

class MemberServiceTest {
//     MemberService memberService;
//     MemoryMemberRepository memoryMemberRepository;

//     @BeforeEach
//     public void BeforeEach(){
//         this.memoryMemberRepository = new MemoryMemberRepository();
//         this.memberService = new MemberService(this.memoryMemberRepository);
//         System.out.println("Test> this.memberService.hashCode(): "+this.memberService.hashCode());
//         System.out.println("Test> this.memberMemberRepository.hashCode(): "+this.memoryMemberRepository.hashCode());
//         System.out.println("-------------------");
//     }
//     @AfterEach
//     public void afterEach(){
//         memoryMemberRepository.clearStore();
//     }

//     @Test
//     void join() {
//         Member member = new Member();
//         member.setName("spring");

//         Long saveId = memberService.join(member);

//         Member findMember = memberService.findOne(saveId).orElseThrow();
//         Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
//     }

//     @Test
//     public void duplicateMember(){
//         Member member1 = new Member();
//         member1.setName("spring");
//         Member member2 = new Member();
//         member2.setName("spring");

//         memberService.join(member1);
//         IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//         Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


// //        try {
// //            memberService.join(member2);
// //        }
// //        catch(IllegalStateException e){
// //            System.out.println("Successfully Error occur\n>  "+e);
// //        }

//     }

//     @Test
//     void findMembers() {
//     }

//     @Test
//     void findOne() {
//     }
}