package jaejung.springprac.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> login(UserDto userDto){
        User user = userRepository.findUserByUserId(userDto.getUserId()).get();
        return Optional.of(UserDto.builder()
                .userId(user.getUserId())
                .password(user.getUserPw())
                .userSt(user.getUserSt())
                .username(user.getUserName())
                .userSq(user.getId())
                .build());
    }

//    public void signUpUser(@Valid UserDto userDto){
//        userRepository.save(userDto.toEntity());
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void saveMultipleUser(){
//        for (int i = 0; i < 10; i++) {
//            if(i==5) throw new RuntimeException();
//            saveUser(i);
//        }
//    }
//
//    public void createUserListWithoutTrans(){
//        for (int i = 0; i < 10; i++) {
//            if(i==5) throw new RuntimeException();
//            saveUser(i);
//        }
//    }
//
//    public User saveUser(int index){
//        User user = User.builder()
//                .username("testname::"+index)
//                .password("testpassword::"+index)
//                .email("testemail::"+index)
//                .role("user")
//                .createDate(new Timestamp(System.currentTimeMillis()))
//                .build();
//
//        userRepository.save(user);
//        return user;
//    }
}
