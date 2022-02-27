package Ch5_제네릭.Item31;

import java.util.*;

// Person 클래스
class Person {
    String name;

    // 기본 생성자
    Person() {
    }

    // 생성자 오버로딩
    Person(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

// Person 상속 Man 클래스
class Man extends Person {
    // 위의 예제와 동일
    Man(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

// Person 상속 Woman 클래스
class Woman extends Person {
    // 위의 예제와 동일
    Woman(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

public class ExtendsSuperEx {

    public static void main(String[] args) {

        // Person
        List<Person> listP = new ArrayList<Person>();
        listP.add(new Person("사람"));
        listP.add(new Person("인간"));
        extendsPerson(listP); // Person과 그 하위 클래스로 제한하므로 Person 가능
        superMan(listP);    // Man과 그 상위 클래스로 제한하므로 Person 가능
//        extendsMan(listP);  //  Man과 그 하위클래스로 제한하므로 x
        // Man
        List<Man> listM = new ArrayList<Man>();
        listM.add(new Man("하현우"));
        listM.add(new Man("박효신"));
        superMan(listM); // 하현우 박효신 : Man은 자기자신 클래스이므로 가능
        extendsPerson(listM); // 하현우 박효신 : Man은 Perosn 클래스 상속받은 하위클래스이기 때문에 가능
        extendsMan(listM);  // 하현우 박효신 : Man과 그 하위클래스만 가능하므로 가능
        // Woman
        List<Woman> listW = new ArrayList<Woman>();
        listW.add(new Woman("백예린"));
        listW.add(new Woman("박정현"));
//        superMan(listW); //→ Woman은 Man 클래스의 상위 클래스가 아니기 때문에 메소드 호출 불가
        extendsPerson(listW); // 백예린 박정현 : Woman은 Person 클래스를 상속받은 하위클래스이기 때문에 가능
//        extendsMan(listW);  // Man과 그 하위클래스만 가능하므로 x
    }

    // Man 클래스와 그 상위 클래스로 생성된 인스턴스만 매개변수로 전달 가능
    public static void superMan(List<? super Man> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
    // Person 클래스와 그 하위 클래스로 생성된 인스턴스만 매개변수로 전달 가능
    public static void extendsPerson(List<? extends Person> list) {
        for (Person obj : list) {
            System.out.println(obj);
        }
    }
    // Man 클래스와 그 하위 클래스로 생성된 인스턴스만 매개변수로 전달 가능
    public static void extendsMan(List<? extends Man> list) {
        for (Person obj : list) {
            System.out.println(obj);
        }
    }
/*
? extends 클래스 : 매개변수의 자료형을 특정 클래스를 상속받은 클래스로만 제한함
? super 클래스 : 매개변수의 자료형을 특정 클래스와 그 클래스의 상위 클래스로만 제한함
 */
}