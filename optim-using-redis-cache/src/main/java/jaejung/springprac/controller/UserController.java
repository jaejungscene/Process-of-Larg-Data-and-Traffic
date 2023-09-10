package jaejung.springprac.controller;

import jaejung.springprac.domain.user.UserService;
import jaejung.springprac.domain.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//    @PostMapping("/register")
//    public void signUpUser(@RequestBody @Valid UserDto userDto){
//        LOGGER.warn("UserDto -> {}", userDto);
//        userService.signUpUser(userDto);
//    }



    @GetMapping("/test")
    public String test(){
        return "user controller";
    }

//    @GetMapping("/test1")
//    public void test1() {
//        userService.saveMultipleUser();
//    }
//
//    @GetMapping("/test2")
//    public void test2() {
//        userService.createUserListWithoutTrans();
//    }
}
