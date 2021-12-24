package ch6_열거타입과_애너테이션.Item45;

import java.math.BigInteger;
import java.util.stream.Stream;

public class MersennePrime {

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime)   // prime인 BigInteger보다 더 큰 첫번째 integer를 반환
    }

    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))     // 소수를 사용해 메르센 수 계산
                .filter(mersenne -> mersenne.isProbablePrime(50))   // 결괏값이 소수인 경우만 남긴다. (매직넘버 50이 소수성 검사가 true를 반환할 확률을 제어)
                .limit(20)      // 결과 스트림의 원소 수를 20개로 제한
                .forEach(System.out::println);  // 종단 연산. 결과 출력
    }
}
