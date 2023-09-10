package com.example.fastcampusmysql.util;


import com.example.fastcampusmysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;
import java.util.Random;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {
    static public EasyRandom get(
            Long memberIdLowerbound, Long memberIdUpperbound,
            LocalDate firstDate, LocalDate lastDate
    ){
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(memberIdPredicate, () -> new Random().nextLong(memberIdLowerbound,memberIdUpperbound+1))
                .dateRange(firstDate, lastDate);
        return new EasyRandom(param);
    }
}
