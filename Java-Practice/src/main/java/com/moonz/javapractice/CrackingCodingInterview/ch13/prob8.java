package com.moonz.javapractice.CrackingCodingInterview.ch13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 람다 랜덤 : 람다 표현식을 사용하여 임의의 부분 집합을 반환하는 함수 `List getRandomSubset(List<Integer> list)` 작성하라.
 * 단, 모든 부분 집합이 선택될 확률은 같아야 한다.
 */
public class prob8 {
    Random random = new Random();
    // Predicate 반환하는 메서드 따로 정의 -> 재사용성!
    Predicate<Object> getRandomBoolean = obj -> {
        return random.nextBoolean();
    };

    List<Integer> getRandomSubset(List<Integer> list) {
        List<Integer> subset = new ArrayList<>();
        return list.stream().filter(getRandomBoolean)
                .collect(Collectors.toList());
    }
}
