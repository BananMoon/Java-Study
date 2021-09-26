package ch11;

import java.util.*;

public class HashSetEx2_Lotto {
    public static void main(String[] args) {
        Set set = new HashSet();

        for (int i=0; i<6; i++) {
            int num = (int)((Math.random()*45) +1);
            set.add(new Integer(num));
        }
        System.out.println(set);    //[40, 10, 27, 44, 29, 14]
        //정렬하기위해 Collections클래스의 sort(list) 필요
        //sort(): List 인터페이스 타입 필요로 하므로 LinkedList 사용해서 HashSet 인자들을 LinkedList에 담는다!
        List list = new LinkedList(set);
        Collections.sort(list);
        System.out.println(list);   //[10, 14, 27, 29, 40, 44]
    }
}