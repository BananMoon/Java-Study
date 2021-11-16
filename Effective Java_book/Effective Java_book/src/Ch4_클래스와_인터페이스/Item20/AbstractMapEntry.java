package Ch4_클래스와_인터페이스.Item20;

import java.util.Map;
import java.util.Objects;

// 추상 클래스
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    // 변경 가능한 엔트리 : setValue 메서드를 반드시 재정의해야한다. -> 변경하려하면 error를 던진다.
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    // Map.Entry.equals의 일반 규약을 구현
    //Object의 메서드들 (equals, hashCode, toString ..) 은 default로 재정의하면 안된다!
    @Override
    public boolean equals(Object o) {
        if (o == this ) return true;
        if (!(o instanceof Map.Entry)) return false;
        Map.Entry<?, ?> e = (Map.Entry)o;
        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());   // ^ : 해당 자리수에서 서로 다른 경우 1을 반환하고, 같은 경우 0을 반환
    }

    @Override
    public String toString() {
        return getKey() + " = " + getValue();
    }

}