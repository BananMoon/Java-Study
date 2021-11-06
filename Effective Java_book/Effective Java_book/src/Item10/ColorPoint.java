package Item10;
// 2-2. 추이성 예제

import java.awt.*;

public class ColorPoint extends Point{
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);    // 상위 클래스인 Point의 생성자 호출
        this.color = color;
    }
    //...
    // 상황 1. ColorPoint에서 equals()를 오버라이딩하지 않는다.
    // 문제 1 : equals()를 호출했을 때 색상 정보는 무시된채 비교를 수행한다.
    /*
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorPoint )) return false;   // ColorPoint의 인스턴스가 아니면 false
        // obj가 ColorPoint의 인스턴스이면, Point의 equals() 비교 & color 비교
        return super.equals(obj) && ((ColorPoint)obj).color == this.color;
    }*/
    //  상황 2. ColorPoint 비교를 추가하는 equals()를 오버라이드한다.
    // 문제 2 : 대칭성 (a.equals(b)가 true면 b.equals(a)도 true) 위배!
    //  -> p.equals(cp)는 true, cp.equals(p)는 false

    // 상황 3. ColorPoint의 equals()에서 Point와 비교할 때는 색상을 무시한다.
    // 문제 3. 추이성 위배
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;     // Point의 인스턴스가 아니라면 false (ColorPoint도 Point의 인스턴스 o)
        if (!(obj instanceof ColorPoint))  {                   // ColorPoint의 인스턴스가 아니라면 (Point라면)
            return obj.equals(this);            //   색상을 무시하고 비교
            //System.out.println(obj);           // obj는 p, this는 cp
        }
        // obj가 ColorPoint의 인스턴스라면 super의 equals()로 POINT 비교 + 색상까지 비교
        return super.equals(obj) && ((ColorPoint) obj).color == this.color;
    }

}
