package Item10;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Set;

public class EqualsEx1 {
    public static void main(String[] args) {
        String s1 = new String("Java");
        String s2 = new String("Java");
        System.out.println(s1.equals(s2));  //true

        // String의 재정의된 equals() 메서드
        // 1. 자기 자신과 값이 같은지 (==) check
        // 2. String 타입인지 check (instanceof String) + Latin1/UTF16 처리

        StringBuilder s3 = new StringBuilder("Java");
        StringBuilder s4 = new StringBuilder("Java");
        System.out.println(s3.equals(s4));  //false : StringBuilder에서는 따로 재정의하지 않았으므로, 주소값을 비교하기 때문에 false


    }
}
