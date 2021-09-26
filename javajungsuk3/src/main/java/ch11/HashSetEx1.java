package ch11;

import java.util.HashSet;
import java.util.Set;

// Set인터페이스의 대표적인 컬렉션
// 순서x, 중복x (순서o, 중복x : LinkedHashSet)

public class HashSetEx1 {
    public static void main(String[] args) {
        Object[] objArr = {"1", new Integer(1), "2","2","3","3","4","4","4"};
        Set set = new HashSet();

        for (int i=0; i<objArr.length; i++) {
            set.add(objArr[i]);
        }
        System.out.println(set);
    }
}
