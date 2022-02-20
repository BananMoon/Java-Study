package com.moonz.javapractice.ReflectionEx;

import java.lang.annotation.*;

@Retention(RetentionPolicy. RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})  // 타입과 필드에만 붙이기 가능
@Inherited      // 상위 클래스의 에노테이션까지 상속이 되도록 설정
public @interface MyAnnotation {
    // primitive, reference type, List 등 가능
    // default를 주어야 값을 안줘도 에러가 안남.
    String name() default "moonz";

    int number() default 7;
}
