package ch6_열거타입과_애너테이션.Item34;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum lastOperation_Ex3_3 {
    //생성자가 있어야 가능
    // public static final 이 default
    PLUS("+") {
        public double apply(double x, double y) { return x+y;}
    },
    MINUS("-") {
        public double apply(double x, double y) { return x-y;}
    },
    TIMES("*") {
        public double apply(double x, double y) { return x*y;}
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x/y;}
    };
    private final String symbol;
    lastOperation_Ex3_3(String symbol) {
        this.symbol = symbol;   // 상수변수

    }  // 생성자

    // toString 재정의
    public String toString() { return symbol; }

    // toString을 통해 변환한 문자열
    // Stream : 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자
    // Iterator와 비슷한 역할을 하지만 람다식으로 요소 처리 코드를 제공하여 코드가 좀 더 간결하게 할 수 있다는 점과
    // 내부 반복자를 사용하므로 병렬처리가 쉽다.
    // Stream.of(a) : a에 대한 스트림 객체 생성
    //  .collect() : Stream의 아이템들을 원하는 자료형으로 변환
    private static final Map<String, lastOperation_Ex3_3> stringToEnum = Stream
            .of(values())
            .collect(Collectors.toMap(Object::toString, e -> e));
    // fromString 재정의 : toString을 통해 변환한 문자열 ->  열거타입 상수
    public static Optional<lastOperation_Ex3_3> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
    // 추상메서드
    public abstract double apply(double x, double y);

    // main method
    public static void main(String[] args) {
        double x = Double.parseDouble("2.0");
        double y = Double.parseDouble("4.0");
        for (lastOperation_Ex3_3 op : lastOperation_Ex3_3.values())
            System.out.printf("%f %s %f = %f\n", x, op, y, op.apply(x,y));
    }
}
