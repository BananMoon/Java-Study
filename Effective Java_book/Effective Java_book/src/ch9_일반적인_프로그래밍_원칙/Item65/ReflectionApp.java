package ch9_일반적인_프로그래밍_원칙.Item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class ReflectionApp {

    public static void main(String[] args) {
        Class<? extends Set<String>> cl = null;
        try {
            // 비검사 형변환
//            Class<?> cl1 = Class.forName(args[0]);
            cl = (Class<? extends Set<String>>)Class.forName(args[0]);  // 주어진 문자열 이름을 가진 클래스 또는 인터페이스와 연관된 객체를 반환
            System.out.println(cl.getName());      // ch9_일반적인_프로그래밍_원칙.Item65.Reflection_Ex1

        } catch (ClassNotFoundException e) {
            fatalError("클래스를 찾을 수 없습니다.");
        }

        // 2. 생성자를 얻는다.
        Constructor<?extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor(null);
            System.out.println(cons);     // public ch9_일반적인_프로그래밍_원칙.Item65.Reflection_Ex1()

        } catch (NoSuchMethodException e) {
            fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
        }

        // 3. 인스턴스를 만든다.
        Set<String> stringSet=null;
        try {
            stringSet = cons.newInstance();
//            reflectionInstance.stringSet.addAll(Arrays.asList(args).subList(1, args.length));  // args 1번째 인자부터 끝까지.. (0번째는 클래스명)
//            Arrays.stream(reflectionInstance.stringSet.toArray()).forEach(System.out::println);
        } catch (InstantiationException e) {
            fatalError("클래스를 인스턴스화 할 수 없습니다.");
        } catch (IllegalAccessException e) {
            fatalError("생성자에 접근할 수 없습니다.");
        } catch (InvocationTargetException e) {
            fatalError("생성자가 예외를 던졌습니다.");
        } catch (ClassCastException e) {
            fatalError("Set을 구현하지 않은 클래스입니다.");
        }
        stringSet.addAll(Arrays.asList(args).subList(1, args.length));
        Arrays.stream(stringSet.toArray()).forEach(System.out::println);


/*        // 1. 클래스를 얻는다. : 클래스 이름을 Class 객체로 변환
        Class<? extends Set<String>> cl = null;
        try {
            // 비검사 형변환
            cl = (Class<? extends Set<String>>) Class.forName(args[0]);  // 주어진 문자열 이름을 가진 클래스 또는 인터페이스와 연관된 객체를 반환
            System.out.println(cl.getName());      // ch9_일반적인_프로그래밍_원칙.Item65.Reflection_Ex1

        } catch (ClassNotFoundException e) {
            fatalError("클래스를 찾을 수 없습니다.");
        }

        // 2. 생성자를 얻는다.
        Constructor<? extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor();
            System.out.println(cons);     // public ch9_일반적인_프로그래밍_원칙.Item65.Reflection_Ex1()

        } catch (NoSuchMethodException e) {
            fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
        }

        // 3. 인스턴스를 만든다.
        Reflection_Ex1 s;
        Set<String> s1 = null;
        try {
//            s1 = cons.newInstance(Arrays.asList(args).subList(1, args.length));// 생성자 객체로 새 인스턴스 생성 가능
            s1 = cons.newInstance();
        } catch (InstantiationException e) {
            fatalError("클래스를 인스턴스화 할 수 없습니다.");
        } catch (IllegalAccessException e) {
            fatalError("생성자에 접근할 수 없습니다.");
        } catch (InvocationTargetException e) {
            fatalError("생성자가 예외를 던졌습니다.");
        } catch (ClassCastException e) {
            fatalError("Set을 구현하지 않은 클래스입니다.");
        }
        // 생성한 집합을 사용
        s1.addAll(Arrays.asList(args).subList(1, args.length));  // args 1번째 인자부터 끝까지.. (0번째는 클래스명)
        System.out.println(s1);
    */
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
