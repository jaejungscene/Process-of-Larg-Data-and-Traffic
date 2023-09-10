package jaejung.springprac.domain.account;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long money;

    @Builder
    public Account(Long id, String name, Long money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public void plusMoney(Long amount){
        this.money += amount;
    }
    public void minusMoney(Long amount){
        this.money -= amount;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
