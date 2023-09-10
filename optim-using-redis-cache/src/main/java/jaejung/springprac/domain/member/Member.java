package jaejung.springprac.domain.member;

import java.sql.Date;

import jaejung.springprac.domain.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Setter //안전을 위해 삭제 권장
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    // @Transient
    private Integer age;

    @Column
    // @Temporal(TemporalType.DATE)
//    private Date birthDate;
    private String birthDate;

    @Column
    // @Enumerated(EnumType.STRING)
//    private Gender gender;
    private String gender;

    @Builder
    public Member(Long id, String name, Integer age, String birthDate, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Member update(String name){
        this.name = name;
        return this;
    }
    
}
