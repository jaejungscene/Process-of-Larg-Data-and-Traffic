package jaejung.springprac.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String email;
    private String userSt;
    private String role;
    @CreationTimestamp
    @Column(name = "createDate")
    private Timestamp createDate;

    @Builder
    public User(Long id, String userId, String userPw, String userName,
    String email, String role, String userSt, Timestamp createDate) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.userSt = userSt;
        this.createDate = createDate;
    }
}
