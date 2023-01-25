package com.moonz.javapractice.CrackingCodingInterview.ch13;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

/**
 * 람다표현식 : 대륙의 총 인구수를 계산하는 메서드 getPopulation() 작성하라.
 * Country 클래스 존재
 * - `getContinent()` : 대륙 이름 getter
 * - `getPopulation(List<Country> 국가들, String 대륙명)`
 */
public class prob7 {

    private static class Country {
        @Getter
        int population;
        String name;
        @Getter
        String continent;
        // 람다 표현식 O
        public int getPopulation(List<Country> countries, String continent) {

            // 1. 특정 대륙에 해당되는 국가 리스트 필터링 stream
            Stream<Country> equalsContinent = countries.stream().filter(country -> {
                return country.continent.equals(continent);
            });

            // 2. 대륙의 인구 수만으로 구성된 stream 추출
            Stream<Integer> populations = equalsContinent.map(country -> country.getPopulation());

            /** 1+2번 : 아래와 같이 특정 대륙에 포함되지 않는 국가의 인구수를 0으로 넘길 수 있다.
             Stream<Integer> populations = countries.stream().map(country ->
                country.getContinent().equals(continent) ? country.getPopulation() : 0
             );
             */

            // 3. reduce 이용하여 인구 수 스트림 순회하며 값 ++
            // 메서드 레퍼런스 populations.reduce(Integer::sum); 로도 구현 가능하지만 Optional로 반환함.
            int population = populations.reduce(0, (a, b) -> a + b);
            return population;
        }


        // 람다 표현식 x
        public int getPopulationWithoutLambda(List<Country> countries, String continent) {
            int total = 0;
            for (Country country : countries) {
                if (country.getContinent().equals(continent)) {
                    total += country.getPopulation();
                }
            }
            return total;
        }
    }
}
