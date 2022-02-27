package ch6_열거타입과_애너테이션.Item34;

public enum SwitchOperation_Ex3_1 {
    PLUS, MINUS, TIMES, DIVIDE;

    // 상수값에 따라 분기하여 연산 수행
    public double apply(double x, double y) {
        switch (this) {
            case PLUS: return x+y;
            case MINUS: return x-y;
            case TIMES: return x*y;
            case DIVIDE: return x/y;
        }
        throw new AssertionError("알 수 없는 연산: "+this);
    }
    // 깨지기 쉬운 코드. 상수를 추가하면 case문도 추가해야한다. 이를 깜빡하면 추가한 상수의 연산을 수행할 때 런타임 오류를 낸다.
    // 해결: 상수별로 다르게 동작하는 코드 구현 -> apply 추상메서드 선언 -> 각 상수별 클래스 몸체에 자신에 맞게 재정의
}
