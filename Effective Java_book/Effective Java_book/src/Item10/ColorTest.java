package Item10;

import java.awt.*;

public class ColorTest {
    public static void main(String[] args) {
        // 상황 2.  대칭성(a.equals(b)가 true면 b.equals(a)도 true) 위배!
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);

        System.out.println(p.equals(cp));   // true.
        System.out.println(cp.equals(p));   // false.

        // 상황 3. 대칭성 지키지만(둘다 true), 추이성 (cp1.equals(p2)가 true고 p2.equals(cp3)이 true면 cp1.equals(cp3)도 true) 위배!
        // 3-1. 무한 재귀 빠지는 위험 발생
        ColorPoint cp1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint cp3 = new ColorPoint(1, 2, Color.BLUE);

        System.out.println(cp1.equals(p2));     //true
        System.out.println(p2.equals(cp3));     // true
        System.out.println(cp1.equals(cp3));    //false. 색상까지 비교하므로

        // => 모든 객체 지향 언어의 동치 관계에서 나타나는 근본적인 문제.
        // 구체 클래스를 확장해 새로운 값을 추가하면서 equals() 규약을 만족시킬 방법은 존재하지 않는다.
        // 객체 지향적 추상화 이점을 포기하지 않는 한!
        // => equals() 안의 instanceof 검사를 getClass 검사로 바꾸면 규약 지킴 + 구체 클래스 상속 + 값 추가 할 수 있다?
        // => getClass(로딩된 클래스의 주소값 만을 리턴하는 메서드)로도 해결 X
    }
}
