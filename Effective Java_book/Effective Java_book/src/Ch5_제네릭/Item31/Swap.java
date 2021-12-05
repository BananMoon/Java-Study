package Ch5_제네릭.Item31;

import java.util.List;

public class Swap {
    public static void swap(List<?> list, int i, int j) {  // 비한정적 와일드카드
        list.set( i, list.set(j, list.get(i)));   // i번째 값을 j번째에 설정한다. i번째에 j번째값을 set한다.
        // error : 'set(int, capture<?>)' in 'java.util.List' cannot be applied to '(int, capture<?>)' -> 원소를 리스트에 넣을 수 없다?
        // 와일드카드 타입인 lIst<?>에는 null 외의 어떤 값도 넣을 수없기 때문!
        // 타입을 실제 타입으로 바꿔주는 helper 메서드를 private으로 두고 호출해야 한다.
        // swapHelper(list, i, j);

    }
    private static void swapHelper(List<E> list, int i, int j) {
        list.set( i, list.set(j, list.get(i)));
    }
}
