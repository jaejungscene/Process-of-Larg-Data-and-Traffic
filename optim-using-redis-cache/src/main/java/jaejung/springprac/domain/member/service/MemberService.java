package jaejung.springprac.domain.member.service;

import jaejung.springprac.domain.member.Member;
import jaejung.springprac.domain.member.MemberDto;
import jaejung.springprac.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//    final private SpringDataJpaMemberRepository memberRepository;
    /**
     * 회원가입
     */
    public MemberDto registerMember(MemberDto member){
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        return EntityToDto(
                memberRepository.save(
                        DtoToEntity(member)
                )
        );
    }


    private void validateDuplicateMember(MemberDto member){
        memberRepository.findByName(DtoToEntity(member).getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<MemberDto> findAllMembers(){
        return memberRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .toList();
    }

    public MemberDto findOneBy(Long memberId){
        Optional member = memberRepository.findById(memberId);
        if(member.isEmpty())
            return null;
        return this.EntityToDto((Member)member.get());
    }

    public MemberDto findOneBy(String name){
        Optional member = memberRepository.findByName(name);
        if(member.isEmpty())
            return null;
        return this.EntityToDto((Member)member.get());
    }

//    public List<MemberDto> findAllBy(Integer age) {
//        return memberRepository.findByAge()
//    }

    public MemberDto deleteOneMemberById(Long memberId){
        Optional member = memberRepository.findById(memberId);
        if(member.isEmpty())
            return null;
        return this.EntityToDto((Member)member.get());
    }

    private MemberDto EntityToDto(Member member){
//        return new MemberDto(
//                member.getId(),
//                member.getName(),
//                member.getAge(),
//                member.getBirthDate(),
//                member.getGender()
//        );
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .build();
    }

    private Member DtoToEntity(MemberDto member){
        return Member.builder()
                .id(member.id())
                .name(member.name())
                .age(member.age())
                .birthDate(member.birthDate())
                .gender(member.gender())
                .build();
    }


}
