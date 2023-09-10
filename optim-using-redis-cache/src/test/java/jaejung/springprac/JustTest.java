package jaejung.springprac;

import jaejung.springprac.domain.employee.Employee;
import jaejung.springprac.domain.user.UserDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import jakarta.persistence.*;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.Gson;


class JustTest {
    @Test
    void objectToJson() throws IllegalAccessException {
        UserDto userDto = UserDto.builder()
                .userId("asdf")
                .email("ljj@asdf.com")
                .username("zxcv")
                .password("qwer")
                .userSt("uiop")
                .userSq(12L)
                .build();

        JSONObject obj = new JSONObject();
        obj.put("userName", userDto.getUserName());
        obj.put("userEmail", userDto.getEmail());
        System.out.println(obj);
    }

    @Test
    void findAny_findFirst() {
        List<String> list = Arrays.asList("A","B","C","D");

        Optional<String> result = list.stream().findAny();
        System.out.println(result.get());

        result = list.stream().parallel().findAny();
        System.out.println(result.get());

        result = list.stream().findFirst();
        System.out.println(result.get());

//        assertTrue(result.isPresent());
//        assertThat(result.get(), anyOf(is("A"), is ("B"), is("C"), is("D")));
   }
   void dotArgTest(String... a){
        System.out.println(Arrays.toString(a));
   }

    @Test
    void staticClass(){
        dotArgTest("as");
        dotArgTest("as", "rew");
        dotArgTest("as", "rew", "zxcv");
        dotArgTest(new String[]{"asdf", "zxcv"});
    }
}