package ch11;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorEx1 {
    public static void main(String[] args) {
        //Iterator는 모든 컬렉션(Set, list1 ..)에 저장된 요소들을 읽어오는 방법을 표준화한 인터페이스
        // But, Map은 Key:Value 쌍이기 때문에 바로 Iterator가 접근할 수 없고, Set(keySet(), entrySet())으로 변환 후 iterator를 이용할 수 있다.
        ArrayList list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");

        Iterator it = list1.iterator();

        while(it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);
        }
    }
}
