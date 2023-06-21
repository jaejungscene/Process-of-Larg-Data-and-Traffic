package com.example.fastcampusmysql;

import lombok.*;
import org.junit.jupiter.api.Test;

record Rectang(
        double length, double width
){

}

@Getter
@Builder
class tempClass{
    private String name;
    private Long id;
}

public class TempTest {
    @Test
    void recordClass_Test(){
        System.out.println("---------------------");
        Rectang temp = new Rectang(12, 24);
        System.out.println(">>>>>>>>> " + temp);
        System.out.println(temp.length());
        System.out.println(temp.width());
        System.out.println("---------------------");
    }

    @Test
    void testLombok() {
        tempClass A = tempClass.builder()
                .name("hello")
                .id(312L)
                .build();
        System.out.println("------------------");
//        System.out.println(A);
//        System.out.println(A.getId());
//        System.out.println(A.getName());
        System.out.println("------------------");
    }

    @Test
    void test01() {
        System.out.println(">>> " + tempClass.class);
        var sql = String.format("SELECT * FROM %s WHERE id = :id", "hello world");
        System.out.println(sql);
    }
}
