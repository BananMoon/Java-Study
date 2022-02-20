package com.moonz.javapractice.DI;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {
    public static <T> T getObject(Class<T> classType) {    // 제네릭 메서드 :  Class<T>를 넣으면 인스턴스 T를 리턴한다.
        T instance = createInstance(classType);   // 현재 상황에서 BookRepository, BookService를 참조할 수 없으니 리플렉션 이용
        // Repository 찾기
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> { // 클래스에 선언되어있는 필드들 중
            if (f.getAnnotation(Inject.class) != null) {                                // Inject 어노테이션이 있는 필드
                Object fieldInstance = createInstance(f.getType()); // Inject 어노테이션이 있는 필드의 클래스 타입
                f.setAccessible(true);
                try {
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return  instance;

    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
