package Ch5_제네릭.Item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Chooser를 제네릭으로 만들기 위한 첫시도.
 *  + Object 배열을 T[]로 형변환 (line 18)
 * Unchecked cast: 'java.lang.Object[]' to 'T[]' : 제네릭이 무슨 타입인지 모르니 런타임에서 안전 보장할 수 없다는 경고 메시지
 */
public class Tried1Chooser<T> {
    private final T[] choiceArray;

    public Tried1Chooser(Collection<T> choices) {
        choiceArray = (T[])choices.toArray();    // 해결:  T[]를 필요로하는데 Object[]를 제공하므로 형변환해준다!
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();   // 현재 thread's ThreadLocalRandom을 반환
        return choiceArray[rnd.nextInt(choiceArray.length)];    // O==
    }

    public static void main(String[] args) {
        List<Student> listImpl = new ArrayList<>();
        listImpl.add(new Student("문윤지", 201835652));
        listImpl.add(new Student("아무개", 201940650));
        Chooser ch = new Chooser(listImpl); // 타입이 다른 원소가 들어 있다면?
        System.out.println("제네릭추가 시도 클래스 : " + ch.choose());
        // list는 서로 다른 유형을 가진 데이터들로 구성될 수 있음
    }
}
