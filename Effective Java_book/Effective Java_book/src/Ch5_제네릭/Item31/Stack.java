package Ch5_제네릭.Item31;

import java.util.Collection;
import java.util.EmptyStackException;

public class Stack<E> {
    // error
//    public void pushAll(Iterable<E> src) { //  incompatible types: java.lang.Iterable<java.lang.Integer> cannot be converted to java.lang.Iterable<java.lang.Number>
      public void pushAll(Iterable<? extends E> src) {  // E의 하위 타입인 Iterable
        for (E e : src)
            push(e);
    }

    // stack 안의 모든 원소를 주어진 컬렉션으로 옮겨 닮는다.
    // error : 'popAll(java.util.Collection<java.lang.Number>)' in 'Ch5_제네릭.Item31.Stack' cannot be applied to '(java.util.Collection<java.lang.Object>)'
//    public void popAll(Collection<E> collection) {    // Number를 Object로
    public void popAll(Collection<? super E> collection) {
            while (!isEmpty()) {
            collection.add(pop());    // pop() 메서드를 통해 하나씩 꺼내면서 해당 collecton에 add
        }
    }

    // 기존
    private Object[] elements;
    private int size= 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements =  new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push (E e) {
        //ensureCapacity();
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
}