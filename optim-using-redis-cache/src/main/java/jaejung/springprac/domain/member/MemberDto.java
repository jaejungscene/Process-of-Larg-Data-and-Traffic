package jaejung.springprac.domain.member;

import java.sql.Date;

import jaejung.springprac.domain.Gender;
import lombok.Builder;

@Builder
public record MemberDto(
    Long id,
    String name,
    Integer age,
    String birthDate,
//    Date birthDate,
    String gender
//    Gender gender
) {
}
