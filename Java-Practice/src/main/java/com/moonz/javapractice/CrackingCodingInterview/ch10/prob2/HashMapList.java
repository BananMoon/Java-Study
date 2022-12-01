package com.moonz.javapractice.CrackingCodingInterview.ch10.prob2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * HashMap<T, ArrayList<E>> 자료구조를 이용하는 코드보다 더 간단하게 만들어줄 자료구조 클래스
 */
public class HashMapList<T, E> {
    private HashMap<T, ArrayList<E>> map = new HashMap<>();

    public void put(T key, E item) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(item);
    }
    public void put(T key, ArrayList<E> items) {
        map.put(key, items);
    }
    public ArrayList<E> get(T key) {
        return map.get(key);
    }

    public boolean containsKey(T key) {
        return map.containsKey(key);
    }
    /* key에 대응되는 value들 속에 해당 value가 있는가? */
    public boolean containsKeyValue (T key, E item) {
        ArrayList<E> values = get(key);
        if (values == null) return false;
        return values.contains(item);
    }

    /* key들을 Set으로 반환 */
    public Set<T> keySet() {
        return map.keySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
