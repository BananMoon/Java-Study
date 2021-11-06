package Item10;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Set;

public class EqualsEx1 {
    public static void main(String[] args) {
        int i1 = 3;
        int i2 = 3;
        System.out.println(i1==i2);  //true. 기본형 타입들 (int형, char형등)은 Call by Value 형태로 기본적으로 대상에 주소값을 가지지 않는 형태로 사용하기 때문에 값만 비교함.

        String s1 = new String("Java");
        String s2 = new String("Java");
        String s3 = "Java";
        String s6 = "Java";    // 리터널("") 방식으로 생성 시, constant pool 영역으로 찾아가서 이전과 같은 값을 가진 String 객체가 있다면 그 객체의 주소값 반환하여 참조.

        String a = "aaa";
        String b = a;
        String c = new String ("aaa");

        System.out.println(s1.equals(s2));  //true. 데이터 값 (동등성) 비교.
        System.out.println(s1==s2);              //false.   reference 타입. 같은 오브젝트인지(동일성) 주소값을 비교함.
        System.out.println(s3==s6);             // true.

        System.out.println(a==c);             //  false. reference 타입 비교
        System.out.println(a.equals(c));             // true.


        /*String의 재정의된 equals() 메서드
        // 1. 자기 자신과 값이 같은지 (==) check
        // 2. String 타입인지 check (instanceof String) + Latin1/UTF16 처리
         public boolean equals(Object anObject) {
            if (this == anObject) {             // 1. 동일성(주소값) 체크 (Object의 equals메서드)
                return true;
            }
            if (anObject instanceof String) {           // 2. 동등성 체크
                String aString = (String)anObject;
                if (coder() == aString.coder()) {
                    return isLatin1() ? StringLatin1.equals(value, aString.value)
                                      : StringUTF16.equals(value, aString.value);
                 }
             }
             return false;
        }

        */
        StringBuilder s4 = new StringBuilder("Java");
        StringBuilder s5 = new StringBuilder("Java");
        System.out.println(s4.equals(s5));  //false : StringBuilder에서는 따로 equals()를 재정의하지 않았으므로, 주소값을 비교하기 때문에 false


    }
}
