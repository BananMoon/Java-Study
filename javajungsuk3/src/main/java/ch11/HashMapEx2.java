package ch11;

import java.util.*;

public class HashMapEx2 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("김자바", new Integer(90));
        map.put("김자바", new Integer(100));
        map.put("이자바", new Integer(100));
        map.put("강자바", new Integer(80));
        map.put("안자바", new Integer(90));

        Set set = map.entrySet();   //map의 키값 쌍을 Set으로 반환
        Iterator it = set.iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            System.out.println("이름 : " + e.getKey() + ", 점수 : "+ e.getValue());
        }

        set = map.keySet(); //모든 key객체 반환
        System.out.println("참가자 명단 : " + set);

        Collection values = map.values();
        it = values.iterator();

        int total = 0;

        while (it.hasNext()) {
            Integer i = (Integer)it.next();
            total += i.intValue();
        }

        System.out.println("총점 : "+ total);
        System.out.println("평균 : "+ (float)total/set.size());
        System.out.println("최고점수 : "+ Collections.max(values)); //java.util의 Collections는 연산에 유용
        System.out.println("최저점수 : "+ Collections.min(values));
    }
}
