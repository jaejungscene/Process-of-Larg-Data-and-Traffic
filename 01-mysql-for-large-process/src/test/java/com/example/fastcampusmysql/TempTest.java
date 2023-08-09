package com.example.fastcampusmysql;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    void testRandomAndEasyRandom() {
        // given
        Random random = new Random(10L);
        EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().seed(10L));
        // when
        long long1 = random.nextLong();
        long long2 = easyRandom.nextLong();
        // then
        System.out.println(">>> " + long1);
        System.out.println(">>> " + long2);
        Assertions.assertThat(long1).isEqualTo(long2);
    }

    @Test
    void IntStream_test() {
        Double x1 = 13.0;
        System.out.println(">>>>> " + x1.hashCode());
        Stream<Double> s = IntStream.range(0, 5)
                .mapToObj(i -> Double.valueOf(i)/2);
        s.forEach(i -> System.out.println(i + "/" + i.getClass()));
    }

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
    class InnerClass{

    }
    @Test
    void pageable() {
        Pageable request = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC,"name"));
        System.out.println(">>> "+request.getClass().getName());
        System.out.println(">>> "+PageRequest.of(0, 4).getClass().getTypeName());
        System.out.println(">>> "+new InnerClass().getClass().getName());

    }
}
