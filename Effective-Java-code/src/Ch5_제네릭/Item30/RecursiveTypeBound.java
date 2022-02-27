package Ch5_제네릭.Item30;

import java.util.Collection;
import java.util.Objects;

// 재귀적 타입 한정
public class RecursiveTypeBound {

    // 매개변수목록이 <E extends Compatable<E>> : 자기 자신(E)과 비교할 수 있는 모든 타입
    public static <E extends Comparable<? super E>> E max (Collection<? extends E> c) {   // 자기 자신과 비교 가능한 타입인 컬렉션만 인자가 될 수 있다.
        if (c.isEmpty())
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");

        // super는 반환 타입은 (매개변수 타입보다) 더 넓은 범위의 타입을 허용하는게 일반적이니까
        E result = null;    // Collection<? super E> 를 하게 되면 상위까지 가능하므로 제한이 없어지니까 extends로 제한한 것
        for (E e: c)   // 현재 e랑 result를 비교해서 result보다 크면 현재 e를 result에 저장
            if (result == null || e.compareTo(result) > 0)  //  result보다 작거나 같으면 negative, 0  OR result보다 크면 positive
                result = Objects.requireNonNull(e);      // e가 null이면 null 에러 던진다.
        return result;
    }
}
