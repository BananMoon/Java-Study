package Ch4_클래스와_인터페이스.Item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

// 정상적으로 작동하지 않는 클래스

public class InstrumentedHashSet<E> extends HashSet<E> {
        // 추가된 원소의 수
    private int addCount = 0;

    public InstrumentedHashSet (int initCap, float loadFactor) {
        super (initCap, loadFactor);
    }

    public InstrumentedHashSet() {}

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);    // HashSet의 add()메서드 호출
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount+=c.size();     // 값을 더한 후
        return super.addAll(c); // HashSet의 addAll() 메서드 호출 -> HashSet의 addAll() 메서드는 InstrumentedHashSet의 add()를 호출
    }
    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("퉁", "퉁퉁", "퉁퉁퉁"));
    }
}
