package com.moonz.thejava.ReflectionEx;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * (1) 리플렉션 API : 클래스, 인스턴스, 어노테이션 접근 (정보 조회)
 * - 런타임 시 클래스나 인스턴스에 접근 가능한 리플랙션
 * - 클래스로딩이 되면 heap에 로드되서 해당 클래스 타입의 인스턴스가 존재하게 됨
 */
public class BookApp {
    public static void main(String[] args) throws ReflectiveOperationException{
        // =================== 클래스 가져오기 (3가지)
        // 1. 클래스 타입으로 가져올 경우
        Class<Book> bookClass = Book.class;

        // 2. 이미 애플리케이션에 인스턴스가 생성된 경우
        Book bookInstance = new Book();
        Class<? extends Book> bookClass1 = bookInstance.getClass();

        // 3. FQCN (Full Qualified Class Name)만 아는 경우
        // "com.moonz.bookspringboot.ReflectionEx.BookApp"밖에 모르는 경우
        Class<?> aClass = Class.forName("com.moonz.thejava.ReflectionEx.BookApp");

        // =================== 필드들 가져오기
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);  // public 필드들 가져오기
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);  // 모든 필드들 가져오기

        // 값을 가져오기 => 인스턴스 필요
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);      // 접근제어 무시
                System.out.printf("필드 : %s, 필드 값 : %s\n", f, f.get(bookInstance));  // 필드와 필드의 값
            } catch (IllegalAccessException e) {
                e.printStackTrace();    // private 변수에 접근 불가
            }
        });
        // =================== 메서드 가져오기
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        Arrays.stream(bookClass.getDeclaredMethods()).forEach(System.out::println);

        // =================== 생성자 가져오기
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);


        // =================== 수퍼 클래스 가져오기
        System.out.println("수퍼 클래스 : " + SubBook.class.getGenericSuperclass());

        // =================== 수퍼 클래스 가져오기
        System.out.print("상속하는 인터페이스 : " );
        Arrays.stream(SubBook.class.getGenericInterfaces()).forEach(System.out::println);

        // =================== Modifier로 접근 제어자나 타입 가져오기 _필드
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.println(f.get(bookInstance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            int modifiers = f.getModifiers();   // getMo
            System.out.println("해당 modifiers " + modifiers + "를 java language로 바꾸면 : " + Modifier.toString(modifiers));
            System.out.println("static이냐? " + Modifier.isStatic(modifiers));
        });

        // =================== 어노테이션 가져오기
        //  나오지 않는 이유 : 어노테이션은 주석(보다는 기능이 있지만)과 같은 취급을 받기 때문에 바이트 코드까지는 정보를 가지고 있지만, 메모리에 로드될 때 정보가 남지 않기 때문
        // 런타임까지도 정보를 가지고 가고싶다면, 어노테이션 에 @Retention(RetentionPolicy. RUNTIME) 추가
        // @Inherited 붙여야 상속이 되어 하위클래스에서도 조회됨
        System.out.println(Arrays.toString(SubBook.class.getAnnotations()));        // getAnnotations() : 상속되는 어노테이션과 자신의 어노테이션 모두 조회
        Arrays.stream(Book.class.getDeclaredAnnotations()).forEach(System.out::println); // getDeclaredAnnotations() : 해당 클래스에 추가한 어노테이션만 조회
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
//            Arrays.stream(f.getAnnotations()).forEach(System.out::println); // 필드에 붙은 어노테이션 출력
            // 어노테이션 참조도 가능
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                MyAnnotation myAnnotation = (MyAnnotation) a;
                System.out.println(myAnnotation.name() + " " + myAnnotation.number());
            });
        });
    }
}
