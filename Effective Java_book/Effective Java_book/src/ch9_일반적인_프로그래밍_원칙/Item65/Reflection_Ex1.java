package ch9_일반적인_프로그래밍_원칙.Item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// Class라는 클래스 :  클래스들의 정보(클래스의 필드, 메서드, 클래스의 종류(인터페이스 등))를 담는 메타 클래스
// JVM은 이 Class 클래스를 통해 클래스들에 대한 정보를 로드
// 적당히 충분히 사용한 리플렉션 기능
public class Reflection_Ex1 implements Set {
    Set<String> stringSet = null;
    public Reflection_Ex1() {
        this.stringSet = new HashSet<>();
    }

    @Override
    public int size() {
        return stringSet.size();
    }

    @Override
    public boolean isEmpty() {
        return stringSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return stringSet.contains(o);
    }

    @Override
    public Iterator iterator() {
        return stringSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return stringSet.toArray();
    }

    @Override
    public boolean add(Object o) {
        return stringSet.add(o.toString());
    }

    @Override
    public boolean remove(Object o) {
        return stringSet.remove(o.toString());
    }

    @Override
    public boolean addAll(Collection c) {
        return stringSet.addAll(c);
    }

    @Override
    public void clear() {
        stringSet.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return stringSet.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return stringSet.retainAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return stringSet.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
