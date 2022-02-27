package Ch5_제네릭.Item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class SolvedStack2<E> {

    private Object[] elements;
    private int size= 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public SolvedStack2() {
        elements =  new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push (E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
//        E result = elements[--size];    // Error1. Incompatible types.  'E'를 요청하는 Object를 주네?

        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        // Warning2 : Unchecked cast: 'java.lang.Object' to 'E' : 비검사 형변환
            // 해결 : 타입 안전 증명 후 @SuppressWarnings() 추가

        elements[size] = null;  // 참조 해제 ->GC
        return result;
    }

    public boolean isEmpty() {
        return size == 0;   // true면 비어있다는 소리.
    }

    private void ensureCapacity() { // 크기 ++
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2*size + 1);
    }

    // Generic 클래스를 사용하는 맛보기 프로그램
    public static void main(String[] args) {
        SolvedStack2<String> stack = new SolvedStack2<>();
        stack.push("moon"); // String을 push 하면 String -> E 자동 형변환
        stack.push("yoon");
        stack.push("ji");

        while (!stack.isEmpty())
            System.out.println(stack.pop().toUpperCase());  // E 타입 원소 꺼내서 String의 toUpperCase 메서드 호출 : 컴파일러에 의해 자동 형변환
    }
}