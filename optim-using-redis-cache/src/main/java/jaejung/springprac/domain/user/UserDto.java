package jaejung.springprac.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    @NotNull
    private Long userSq;
    @NotNull
    private String userId; //아이디
    @NotNull
    @NotBlank
    private String userPw; //비밀번호
    @NotNull
    private String userName;
    @Email
    private String email;
    private String userSt; //사용자 상태

    @Builder
    public UserDto(Long userSq, String userId, String password, String username, String email, String userSt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userPw = password;
        this.userName = username;
        this.email = email;
        this.userSt = userSt;
    }

//    public User toEntity() {
//        return User.builder()
//                .username(this.username)
//                .email(this.email)
//                .password(this.password)
//                .r(this.role)
//                .build();
//    }
}
