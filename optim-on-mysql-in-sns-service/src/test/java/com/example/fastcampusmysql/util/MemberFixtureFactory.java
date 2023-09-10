package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;

public class MemberFixtureFactory {
    static private EasyRandomParameters param
            = new EasyRandomParameters()
            .stringLengthRange(5,20)
            .excludeField(named("id"));

    static public Member create(){
        return new EasyRandom(param).nextObject(Member.class);
    }

    static public Member create(Long seed){
        return new EasyRandom(param.seed(seed)).nextObject(Member.class);
    }
}
