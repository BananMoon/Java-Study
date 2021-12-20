package ch12;

//class Fruit3 {
//    @Override
//    public String toString() {
//        return "Fruit3";
//    }
//}
//
//class Apple3 extends Fruit3{
//    @Override
//    public String toString() {
//        return "Apple3";
//    }
//}
//
//class Grape3 extends Fruit3 {
//    @Override
//    public String toString() {
//        return "Grape3";
//    }
//}

import java.util.ArrayList;

class Juice {
    String name;
    Juice(String name) { this.name = name+ "Juice"; }

    @Override
    public String toString() {
        return "Juice{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";
        for (Fruit f : box.getList())
            tmp += f+ " ";
        return new Juice(tmp);
    }
}
public class FruitBoxEx3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
//        FruitBox<Toy> grapeBox = new FruitBox<Toy>();  : FruitBox<T extends Fruit> 에서 Toy는 Fruit를 extends하지 않아서 FruitBox<Toy>가 들어갈 수 없음

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.println(Juicer.makeJuice(fruitBox));     // apple  grape juice
        System.out.println(Juicer.makeJuice(appleBox));     // apple  apple juice
    }
}

class FruitBox<T extends Fruit> extends Box<T> {}

class Box<T> {
    ArrayList<T> list = new ArrayList<T>();
    void add (T item) { list.add(item);}
    T get (int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }

    @Override
    public String toString() { return list.toString(); }
}