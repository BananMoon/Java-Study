package ch7_람다와_스트림.Item45;

public class charTest {
    public static void main(String[] args) {
        "Hello world!".chars().forEach(System.out::print);      // 721011081081113211911111410810033
        System.out.println();
        "Hello world!".chars().forEach(x -> System.out.print((char)x));

    }
}
