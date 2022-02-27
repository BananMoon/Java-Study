package Ch5_제네릭.Item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackWithAnnot<E> {
    private E[] elements;
    private int size= 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public StackWithAnnot() {
    //   elements = new E[DEFAULT_INITIAL_CAPACITY]; -> 컴파일오류: E와 같은 실체화 불가 타입으로는 배열을 만들 수 없다.
        // 해결책 1. 대놓고 우회 방식 But 타입 안전하지 않음
        // 우리가 직접 타입이 안전한지 증명하면 @SuppressWarnings 애너테이션으로 해당 경고를 숨길 수 있다.
        // - private 필드이고 클라이언트나 다른 메서드에게 전달될일이 없다.
        // - Push에는 E 타입만 저장되므로 비검사 형변환은 확실히 안전.
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
     }
    public void push (E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
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
}
