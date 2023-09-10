package jaejung.springprac.domain.user;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(UserDto userVo);
}
