package com.moonz.thejava.ReflectionEx;

@MyAnnotation()
public class Book {
    @MyAnnotation()
    private String a = "a";

    private static final String B = "Book";
    private static final String C = "Book";
    public String d = "d";
    protected String e= "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    public void f() {
        System.out.println("F");
    }
    public void g() {
        System.out.println("G");
    }
    public int h() {
        return 100;
    }
}
