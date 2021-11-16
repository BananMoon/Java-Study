package Ch2_객체생성과_파괴.Item7;

import java.util.WeakHashMap;

public class WeakHashMapTest {

    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>(); // WeakReference 특징 이용. HashMap의 Element를 자동으로 제거(GC)

        Integer key1 = 1000;
        Integer key2 = 2000;

        map.put(key1, "test a");
        map.put(key2, "test b");

        key1 = null;

        System.gc();  //강제 Garbage Collection

        map.entrySet().stream().forEach(el -> System.out.println(el));  // 2000=test b

    }
}