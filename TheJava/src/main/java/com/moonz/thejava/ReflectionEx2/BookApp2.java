package com.moonz.thejava.ReflectionEx2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *  * (2) 리플렉션 API : 클래스, 인스턴스, 어노테이션 정보 조회
 */
public class BookApp2 {
    public static void main(String[] args) throws ReflectiveOperationException {
//        Book2.class 처럼 직접 Book을 참조할 수 없는 경우 (대부분)
        Class<?> book2Class = Class.forName("com.moonz.thejava.ReflectionEx2.Book2");
//        Constructor<?> book2Constructor = book2Class.getConstructor();  // 기본 생성자 호출
        Constructor<?> book2ConstructorWithStr = book2Class.getConstructor(String.class);  // 기본 생성자 호출

        Book2 book2 = (Book2) book2ConstructorWithStr.newInstance("myBook");
        System.out.println(book2);
        Arrays.stream(book2Class.getFields()).forEach(System.out::println);
        // 1. static 필드 가져와서 수정
        Field a = Book2.class.getDeclaredField("A");
        System.out.println(a.get(book2));   // static 필드이기 때문에 넘겨줄  특정한 object가 없으므로 null 넣어도 되고 인스턴스 넣어줘도 되고..
        System.out.println(a.get(null));   // static 필드이기 때문에 넘겨줄  특정한 object가 없으므로 null 넣어도 되고 인스턴스 넣어줘도 되고..

        a.set(null, "AAA"); // 값 세팅 시에도 null을 줄 수 있음
        System.out.println(a.get(null));
        // 2. 인스턴스 필드 가져와서 수정
        Field b = Book2.class.getDeclaredField("B");
        b.setAccessible(true);
        System.out.println(b.get(book2));
//        System.out.println(b.get(null));  // 인스턴스 필드이기 때문에 인스턴스를 넘겨줘야 가능
        b.set(book2, "BBBBB");
        System.out.println(b.get(book2));

        // 3. 메서드 수정
        Method c = Book2.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(book2); // 인스턴스, 파라미터 순 호출

        Method sum = Book2.class.getDeclaredMethod("sum", int.class, int.class);    // 파라미터 정보까지 추가해줘야함
        int invoke = (int)sum.invoke(book2, 2, 5);
        System.out.println(invoke);
    }
}
