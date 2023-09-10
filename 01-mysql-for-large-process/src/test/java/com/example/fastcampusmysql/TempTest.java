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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    void pageable() {
        int temp;
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        for(int i=0; i<speeds.length; i++) {
            temp = 100 - progresses[i];
            System.out.println(temp / speeds[i]);
            System.out.println(temp % speeds[i] != 0 ? 1 : 0);
            System.out.println(temp / speeds[i] +
                    temp % speeds[i] != 0 ? 1 : 0);
            System.out.println("--------------");
        }
//        Pageable request = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC,"name"));
//        System.out.println(">>> "+request.getClass().getName());
//        System.out.println(">>> "+PageRequest.of(0, 4).getClass().getTypeName());
//        System.out.println(">>> "+new InnerClass().getClass().getName());
    }
}
