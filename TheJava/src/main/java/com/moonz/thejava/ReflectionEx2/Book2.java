package com.moonz.thejava.ReflectionEx2;

public class Book2 {
    public static String A = "A";

    private String B= "B";

    public Book2() {
    }

    public Book2(String b) {
        System.out.println("생성자에서 b 수정");
        B = b;
    }

    private void c() {
        System.out.println("메서드가 호출되어 C 출력");
    }

    public int sum(int left, int right ) {
        return left + right;
    }
}
