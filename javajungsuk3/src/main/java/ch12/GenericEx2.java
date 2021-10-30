package ch12;
import java.util.ArrayList;

//1.4 제한된 ㅓㅗ지네릭스
// 타입 매개변수 T에 지정할수있는 타입의 종류를 제한 : 상속&구현한 클래스만 받을 수 있다.,
class Fruit2 implements Eatable {
    public String toString() { return "Fruit"; }
}

class Apple2 extends Fruit2 { public String toString() {return "Apple"; }}
class Grape2 extends Fruit2 { public String toString() {return "Grape"; }}
class Toy2 { public String toString() { return "Toy"; }}

interface Eatable{}

public class GenericEx2 {
    public static void main(String[] args) {
        // FruitGeneric의 T는 Fruit2 와 Eatable
        FruitGeneric<Fruit2> fruitGeneric = new FruitGeneric<Fruit2>();
        FruitGeneric<Apple2> appleGeneric = new FruitGeneric<Apple2>();
        FruitGeneric<Grape2> grapeGeneric = new FruitGeneric<Grape2>();

//        FruitGeneric.add(new Fruit2()); //왜 오류나지?
//        FruitGeneric.add(new Apple());
        appleGeneric.add(new Apple2());
//        appleGeneric.add(new Grape2()); // ERROR. 자손이 아니므로
        System.out.println("fruitGeneric-" + fruitGeneric); //[]
        System.out.println("appleGeneric-" + appleGeneric); //appleGeneric-[Apple]
    }
}
class FruitGeneric<T extends Fruit2 & Eatable> extends SampleGeneric2<T> {}  //Fruit2상속, Eatable 구현

class SampleGeneric2<T> {
    ArrayList<T> list = new ArrayList<T>();

    void add(T item) { list.add(item); }
    T get (int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}