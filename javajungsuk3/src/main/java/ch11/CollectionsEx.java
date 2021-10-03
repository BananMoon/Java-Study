package ch11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


public class CollectionsEx {
    public static void main(String[] args) {
        List list1 = new ArrayList();
        System.out.println(list1);

        Collections.addAll(list1, 1,2,3,4,5);
        System.out.println(list1);

        Collections.rotate(list1, 2);    //4,5,1,2,3
        System.out.println(list1);

        Collections.swap(list1, 0,2);    //1,5,4,2,3
        System.out.println(list1);

        Collections.shuffle(list1);
        System.out.println(list1);

        Collections.sort(list1, Collections.reverseOrder()); //역순 정렬
        System.out.println(list1);

        int idx = Collections.binarySearch(list1, 3);    //3이 저장된 index
        System.out.println("index of 3 = " + idx);

        System.out.println("max = " + Collections.max(list1));
        System.out.println("min = " + Collections.min(list1));
        System.out.println("min = " + Collections.max(list1, Collections.reverseOrder()));

        Collections.fill(list1, 9);
        System.out.println("list1 = " + list1);

        // 같은 갯수만큼 생성
        List newList = Collections.nCopies(list1.size(), 2);
        System.out.println("newList = "+ newList);

        // 공통요소 없는지 확인 (있으면 false, 없으면 true)
        System.out.println(Collections.disjoint(list1, newList));    //공통요소 있으면 false

        //복사. copy(a,b) : a에 b를 복사
        Collections.copy(list1, newList);
        System.out.println("newList = " + newList);
        System.out.println("list1 = " + list1);

        Collections.replaceAll(list1, 2,1);    //list1의 2를 1로 대체
        System.out.println("list1 = " + list1);

        Enumeration e = Collections.enumeration(list1);
        ArrayList list2 = Collections.list(e);

        System.out.println("list2 = " + list2);
    }
}

/* 출력
[]
[1, 2, 3, 4, 5]
[4, 5, 1, 2, 3]
[1, 5, 4, 2, 3]
[3, 2, 5, 4, 1]
[5, 4, 3, 2, 1]
index of 3 = 2
max = 5
min = 1
min = 1
list1 = [9, 9, 9, 9, 9]
newList = [2, 2, 2, 2, 2]
true
newList = [2, 2, 2, 2, 2]
list1 = [2, 2, 2, 2, 2]
list1 = [1, 1, 1, 1, 1]
list2 = [1, 1, 1, 1, 1]
 */