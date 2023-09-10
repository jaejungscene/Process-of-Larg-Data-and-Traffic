package jaejung.springprac;

import jaejung.springprac.domain.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserServiceImpl userService;

//    @Test
//    public void test1() {
//        userService.createUserListWithTrans();
//    }

//    @Test
//    public void test2(){
//        userService.createUserListWithoutTrans();
//    }
}
