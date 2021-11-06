package Item10;

import java.util.ArrayList;
import java.util.List;

public class CaseInsensitiveStringTest {
/* String클래스의 equals() 메서드
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String aString = (String) anObject;
        }
        return false;
    }
 */
    public static void main(String[] args) {
        CaseInsensitiveString a = new CaseInsensitiveString("Test");
        String b = "test";

//        CaseInsensitiveString b = new CaseInsensitiveString("test");

        System.out.println("a.equals(b): " + a.equals(b));  // true.  (CaseInsensitiveString의 equals() 호출)
        System.out.println("b.equals(a): " + b.equals(a));  // false.  (String의 equals() 호출 )
        // 대칭성 (a.equals(b)가 true면, b.equals(a)도 true다) 위반!!!
        // 수정 후 결과:
        // a.equals(b): false
        // b.equals(a): false
        System.out.println("========================");

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(a);
        System.out.println(list.contains(b));   // false
    }
}
