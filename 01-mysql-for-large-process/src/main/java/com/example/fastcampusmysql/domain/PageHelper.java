package com.example.fastcampusmysql.domain;

import org.springframework.data.domain.Sort;

import java.util.List;

public class PageHelper {
    public static String orderBy(Sort sort) {
        if (sort.isEmpty()) {
            return "id DESC";
        }
        List<Sort.Order> orders = sort.toList();
        System.out.println(orders);
        System.out.println(orders.getClass());
        System.out.println(orders.size());
        var orderBys = orders.stream()
                            .map(order -> order.getProperty() + " " + order.getDirection())
                            .toList();
        System.out.println(orderBys);
        System.out.println(orderBys.getClass());
        System.out.println(orderBys.size());
        System.out.println(String.join(", ", orderBys));
        return String.join(", ", orderBys);
    }
}
