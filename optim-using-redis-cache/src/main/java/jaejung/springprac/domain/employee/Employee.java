package jaejung.springprac.domain.employee;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
//@Getter
//@Setter //안정을 위해 삭제 권장
//@ToString
@NoArgsConstructor
public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Builder
    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
