package ch11;

import java.util.HashSet;
import java.util.Objects;

public class HashSetEx4 {
    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add("abc");
        set.add("abc");
        set.add(new Person("Moon",23));
        set.add(new Person("Moon",23));

        System.out.println(set);
    }
}

class Person {
    String name;
    int age;
    Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name+": "+age;
    }
}

class Person2 {
    String name;
    int age;
    Person2 (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name+": "+age;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Person2) {
            Person2 tmp = (Person2) obj;
            return name.equals(tmp.name) && age==tmp.age;
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(name,age);
    }
    //세가지 조건
    // 1. 동일한 객체에 대해 여러번 hashCode() 호출 시, 두 객체의 hashcode는 같아야 한다.
    // 2. equals 메서드 값이 true인 두 객체의 hashCode값은 같아야 한다.
    // 3. equals 메서드 값이 false인 두 객체의 hashCode값은 같아도 되지만, 다를 수록 Hashtable이나 HashMap 컬렉션의 성능이 좋아진다.
}