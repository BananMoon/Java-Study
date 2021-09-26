package ch11;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetLotto {
    public static void main(String[] args) {
        Set set = new TreeSet(new TestComp());
        for (int i=0; i<6; i++) {
            int num = (int)(Math.random()*45) + 1;
            set.add(num);
        }
        System.out.println(set);
        //Integer 클래스는 implements Comparable을 상속받기 때문에 정렬기준 없어도 에러 X
        // 만약 set에 num이 아닌 다른 객체를 넣는다면?
        Set set2 = new TreeSet();
        set.add(new Test());
        set.add(new Test());
        set.add(new Test());

        System.out.println(set2);

    }
}
class Test {

}
// 1. TreeSet은 객체 저장 시 비교&정렬을 진행하기 때문에 비교 메서드를 오버라이딩하지않으면 안됨
    // 1-1. 방법1) Test 클래스가 Comparable(기본정렬조건 제공)을 implements
    // 1-2. 방법2) TestComp 클래스를 따로 생성해서 Comparator(별도정렬조건 제공)를 imiplements해서, TreeSet에 인자로 전달해서 비교기능 제공

//class Test implements Comparable {
// 방법1)
//    @Override
//    public int compareTo(Object o) {
//        return -1; //음수는 o1 < o2. 양수: o1>o2. 0: o1==o2
//    }
//}

class TestComp implements Comparator {
// 방법2)
    @Override
    public int compare(Object o1, Object o2) {
        return -1;
    }
}
