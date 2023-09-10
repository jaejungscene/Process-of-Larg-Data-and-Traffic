package jaejung.springprac.controller;

import jaejung.springprac.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jaejung.springprac.domain.member.MemberDto;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    final private MemberService memberService;

    @GetMapping("/all")
    public List<MemberDto> getAllMember() {
        return memberService.findAllMembers();
    }

    @GetMapping("/{memberId}")
    public MemberDto getOneMemberBy(@PathVariable Long memberId) {
        return memberService.findOneBy(memberId);
    }

    @GetMapping("")
    public MemberDto getOneMemberBy(
            @RequestParam(value = "name") String name
    ) {
        return memberService.findOneBy(name);
    }

//    @GetMapping("txtest")
//    public void txTest() {
//        memberService.
//    }


//    @GetMapping("/member/{age}")
//    public List<MemberDto> getMembersBy(@PathVariable Integer age){
//        return memberService.findAllBy(age);
//    }

    @PostMapping ("")
    public MemberDto register(@RequestBody MemberDto member){
        System.out.println(member);
        return memberService.registerMember(member);
    }

    @DeleteMapping ("/{memberId}")
    public MemberDto deleteOneMember(@PathVariable Long memberId){
        return memberService.deleteOneMemberById(memberId);
    }
}
