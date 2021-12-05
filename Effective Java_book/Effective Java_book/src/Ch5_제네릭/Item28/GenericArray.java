package Ch5_제네릭.Item28;

import java.util.List;

/**
 *  책 28-3 코드 : 제네릭 배열 생성을 허용하지 않는 이유
 *  저자는 (5)번에서 에러가 발생한다는데 IntelliJ가 똑똑이라  (1) 에서 발생!
 */
public class GenericArray {
    public static void main(String[] args) {
//        List<String>[] stringArray = new List<String>[1];   // (1). Generic Array Creation error 발생.
//        List<Integer> intArray = List.of(42);  // (2). List.of() : 정적 팩터리 메서드로, 불변 리스트를 반환한다.
//        Object[] objects = stringArray; // (3)
//        objects[0] = intArray;  // (4)
//        String s = stringArray[0].get(0);       // (5)
    }
}
