package ch12;

import java.util.ArrayList;

class Fruit { public String toString() { return "Fruit"; }}
class Apple extends Fruit { public String toString() {return "Apple"; }}
class Grape extends Fruit { public String toString() {return "Grape"; }}
class Toy { public String toString() { return "Toy"; }}
class childGeneric<T> extends SampleGeneric { public String toString() { return "childGeneric의 toString() 출력"; }}

public class GenericEx1 {
    public static void main(String[] args) {
        SampleGeneric<Fruit> fruitGeneric = new SampleGeneric<Fruit>();
        SampleGeneric<Apple> appleGeneric = new SampleGeneric<Apple>();
        // 다형성 : 두 지네릭 클래스 타입이 상속관계에 있고, 대입되는 타입(Toy)이 같으면 객체 생성 가능 -> 자식 클래스의 메서드 호출
        // 다형성? 하나의 객체가 여러 가지 타입을 가질 수 있는 것을 의미.
        // 자바에서는 이러한 다형성을 부모 클래스 타입의 참조 변수로 자식 클래스 타입의 인스턴스를 참조할 수 있도록 하여 구현
        SampleGeneric<Toy> toyGeneric = new childGeneric<Toy>();    // 다형성
        System.out.println(toyGeneric.toString());  //"childGeneric" 출력

//        SampleGeneric<Grape> grapeGeneric = new SampleGeneric<Apple>(); //ERROR. 타입 불일치 (Grape로 변경!)
        SampleGeneric<Grape> grapeGeneric = new SampleGeneric<>();  // JDK1.7부터는 추정이 가능한 경우 타입 생략!

        fruitGeneric.add(new Fruit());
        fruitGeneric.add(new Grape());  //자식 인스턴스 추가 가능
//        appleGeneric.add(new Toy());    //ERROR. SampleGeneric<Apple>에는 Apple만 담을 수 있음.
        appleGeneric.add(new Apple());
        appleGeneric.add(new Apple());
        toyGeneric.add(new Toy());
        System.out.println(fruitGeneric);   // [Fruit, Grape]
        System.out.println(appleGeneric);   // [Apple, Apple]
        System.out.println(toyGeneric);     // childGeneric: 다형성으로 인해 childGeneric의 toString()이 호출됨
    }
}

class SampleGeneric<T> {    // 제네릭 클래스
    //static T item;  // ERROR. static멤버는 인스턴스 변수를 참조할 수 없기 때문에 인스턴스변수로 간주되는 T를 참조 X

//    T[] itemArray;  //배열을 위한 "참조변수" 선언 가능
//    T[] toArray() {
//        T[] tmpArr = new T[itemArray.length];   // ERROR. Type parameter 'T' cannot be instantiated directly
//        return tmpArr;
//    }
    //지네릭 배열 생성해야한다면
    // 1. reflectionAPI의 newInstance() : 동적으로 객체 생성하는 메서드
    // 2. Object 배열을 생성해서 복사한 뒤, 'T[]'로 변환

    ArrayList<T> list = new ArrayList<T>();

    void add(T item) { list.add(item); }
    T get (int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}