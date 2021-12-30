package ch6_열거타입과_애너테이션.Item44;

import java.util.Objects;

public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after); //지정된 개체 참조가 null이 아닌지 확인
        return (T t) -> { accept(t); after.accept(t); };
    }
}

class impl {
    public static void main(String[] args) {

//andThen() 사용시 -> 두개 이상의 Consumer를 연속적으로 실행
        Consumer<String> printString = text -> System.out.println("Miss " + text + "?");
        Consumer<String> printString2 = text -> System.out.println("--> Yes");
        printString.andThen(printString2).accept("me");
    }
}