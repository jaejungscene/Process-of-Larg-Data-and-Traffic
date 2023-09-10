package jaejung.springprac.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
//@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidRequestDto {
    @Phone
    private String phone;

    @NotNull
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private String role;
}
