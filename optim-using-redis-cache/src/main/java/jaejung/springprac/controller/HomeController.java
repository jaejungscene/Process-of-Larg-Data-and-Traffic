package jaejung.springprac.controller;

import jaejung.springprac.domain.user.User;
import jaejung.springprac.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    final private UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginFrom() {
        return "loginForm";
    }
    
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

//    @PostMapping ("/join")
//    public String join(User user) {
//        System.out.println(user);
//        User newUser = User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .email(user.getEmail())
//                .role("user")
//                .build();
//        userRepository.save(newUser);
//        return "join";
//    }
}
