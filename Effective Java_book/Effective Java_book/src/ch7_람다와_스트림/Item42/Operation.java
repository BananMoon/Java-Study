package ch7_람다와_스트림.Item42;

import java.util.function.DoubleBinaryOperator;

public enum Operation {
    // lambda  : 열거 타입 상수의 동작을 표현!
    PLUS("+", (x, y) -> {
//        System.out.println(symbol);   // 런타임에 만들어지는 인스턴스 접근   불가.
        return x + y;
    } ),
    // PLUS("+", (x, y) -> {
    //
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    Operation(String symbol, DoubleBinaryOperator op) { // 열거 타입 상수의 동작을 표현한 람다를 DoubleBinaryOperator 인터페이스 변수에 할당
        this.symbol = symbol;
        this.op = op;
    }

    @Override public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);  // 인터페이스 DoubleBinaryOperator의 메서드 : 매개변수를 double로 매핑
    }

    public static void main(String[] args) {
        Operation plus = Operation.PLUS;
        double result = plus.apply(0.1, 0.4);
        System.out.println("결과 : " + result);  // 결과 : 0.5
    }
}
