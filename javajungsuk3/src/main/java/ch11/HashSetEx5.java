package ch11;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetEx5 {
    public static void main(String[] args) {
        HashSet setA = new HashSet();
        HashSet setB = new HashSet();
        HashSet setHab = new HashSet();
        HashSet setKyo = new HashSet();
        HashSet setCha = new HashSet();
        Iterator it;

        for (int i=1; i<6; i++) {
            setA.add(i+"");
        }
        System.out.println("A = "+setA);
        for (int i=4; i<9; i++){
            setB.add(i+"");
        }
        System.out.println("B = "+setB);

        //교집합
        it = setA.iterator();
        while (it.hasNext()) {
            Object obj =it.next();
            if (setB.contains(obj)) {
                setKyo.add(obj);
            }
        }

        //차집합 A-B
        it = setA.iterator();
        while (it.hasNext()) {
            Object obj =it.next();
            if (!setB.contains(obj)) {
                setCha.add(obj);
            }
        }

        //합집합
        it = setA.iterator();
        while (it.hasNext()) {
            setHab.add(it.next());
        }
        it = setB.iterator();
        while (it.hasNext()) {
            setHab.add(it.next());
        }
        System.out.println("A ∩ B = "+ setKyo);
        System.out.println("A ∪ B = "+ setHab);
        System.out.println("A - B = "+ setCha);

    }


        //contains와 add 메서드 생성하기
}
