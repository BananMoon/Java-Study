package ch11;
//https://www.youtube.com/watch?v=uPSkCKB4Kuo&t=759s
//aroundhub_codeground 영상 참고

import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        System.out.println("-----------------------↓↓↓ ArrayList ↓↓↓---------------------------");

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(100);
        arrayList.add(400);
        arrayList.add(200);
        arrayList.add(300);
        arrayList.add(600);

//        for (int i=0; i<arrayList.size(); i++) {
//            System.out.println(arrayList.get(i));
//        }
        //advanced for문
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }
        System.out.println("----------------------------------");

        arrayList.remove(3);
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }
        System.out.println("----------------------------------");

        //Collections 클래스 : Collection 프레임워크에 있는 클래스들을 편하게 사용하고자 제공되는 클래스
        Collections.sort(arrayList);
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }

        arrayList.set(1, 500);  //index 1을 500으로 set
        System.out.println("----------------------------------");

        // 자바의 컬렉션 프레임워크가 컬렉션에 저장된 요소를 읽어오는 방법으로 표준화한 Iterator<E> 인터페이스
        // hasNext(), next() 등
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("arrayList의 크기 : "+ arrayList.size());   //크기

        System.out.println(arrayList.indexOf(500)); //1
        System.out.println("-----------------------↑↑↑ ArrayList ↑↑↑---------------------------");
        System.out.println("-----------------------↓↓↓ LinkedList ↑↑↑---------------------------");

        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("가");
        linkedList.add("다");
        linkedList.add("나");
        linkedList.add("마");
        linkedList.add("라");

        for (String st : linkedList) {
            System.out.println(st);
        }
        System.out.println("----------------------------------");

        linkedList.remove(3);   //마 삭제

        for (String st : linkedList) {
            System.out.println(st);
        }
        System.out.println("----------------------------------");

        Collections.sort(linkedList);
        for (String st : linkedList) {
            System.out.println(st);
        }

        System.out.println("-----------------------↑↑↑ LinkedList ↑↑↑---------------------------");
        System.out.println("-----------------------↓↓↓ Vector ↓↓↓---------------------------");
    //ArrayList와 거의 동일. 차이점은 Thread-safe

        Vector<String> vector = new Vector<>(); // capacity를 5로 지정할 수도 있음

        System.out.println(vector.size());      // 0
        System.out.println(vector.capacity());  // vector는 기본적으로 10이라는 용량을 갖고 시작.

        vector.add("가");
        vector.add("다");
        System.out.println(vector.add("나"));
        System.out.println(vector.add("마"));
        System.out.println(vector.add("라"));    //true
        System.out.println("----------------------------------");

        System.out.println(vector.size());      // 5

        for (String st : vector) {
            System.out.println(st);
        }
        System.out.println("----------------------------------");

        Collections.sort(vector);
        for (String st : vector) {
            System.out.println(st);
        }

        System.out.println("-----------------------↑↑↑ Vector ↑↑↑---------------------------");
        System.out.println("-----------------------↓↓↓ Stack ↓↓↓---------------------------");

        Stack<Integer> stack = new Stack<>();

        stack.add(1);
        stack.push(3);

        System.out.println(stack.add(7));  // boolean 반환
        System.out.println(stack.push(2)); //값이 반환. 보통 push를 많이 사용!

        System.out.println(stack.size());
        System.out.println("----------------------------------");

        System.out.println(stack.peek());   //LIFO : 마지막 값을 조회.

        System.out.println(stack.size());   // size 변환 x

        System.out.println(stack.pop());    // 조회 후 삭제
        System.out.println(stack.size());   // size 변환 o

        System.out.println("-----------------------↑↑↑ Stack ↑↑↑---------------------------");

    }
}
