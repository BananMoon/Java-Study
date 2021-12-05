package Ch5_제네릭.Item31;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @DisplayName("Number 스택에 Integer 입력")
    @Test
    void 와일드카드노적용push() {
        Stack<Number> stack = new Stack<>();
        Integer foo[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
        Iterable<Integer> iterable = Arrays.asList(foo);
        stack.pushAll(iterable);
    }

    void 와일드카드노적용pop() {
        Stack<Integer> numStack = new Stack<>();
        Collection<Object> objCol = new ArrayList<>(1);
        numStack.popAll(objCol);
    }


}