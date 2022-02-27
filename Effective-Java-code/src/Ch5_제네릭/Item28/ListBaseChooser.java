package Ch5_제네릭.Item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 배열 대신 리스트를 사용하자. (특히 배열과 제네릭을 섞어 썼는데 컴파일 오류/경고가 발생한다면! )
 * 제네릭(T)은 (런타임 시 원소의 타입 정보가 소거되므로) 무슨 타입인지  알 수 없다.
 * 배열은 컴파일 시에 타입 안전하지 않다??? ★★
 */
public class ListBaseChooser<T> {
    private final List<T> choiceArray;

    public ListBaseChooser(Collection<T> choices) {
        choiceArray = new ArrayList<T>(choices);    // 해결:  T[]를 필요로하는데 Object[]를 제공하므로 형변환해준다!
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();   // 현재 thread's ThreadLocalRandom을 반환
        return choiceArray.get(rnd.nextInt(choiceArray.size()));    // Object 배열 반환
    }

    public static void main(String[] args) {
//        List<Student> listImpl = new ArrayList<>();
//        listImpl.add(new Student("문윤지", 201835652));
//        listImpl.add(new Student("아무개", 201940650));
//        Chooser ch = new Chooser(listImpl); // 타입이 다른 원소가 들어 있다면?
//        System.out.println(ch.choose());
    }
}
