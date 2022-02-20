package com.moonz.javapractice;

public class App {
    static String myName;
    static {
        myName = "moonz";
    }
    private String foo = "bar";
    public static void main(String[] args) {
        App app = new App();
        String instance = "heap에 저장되는 instance!";
        System.out.println(BookSpringbootApplication.class.getSuperclass());    // 클래스 객체
    }
}
