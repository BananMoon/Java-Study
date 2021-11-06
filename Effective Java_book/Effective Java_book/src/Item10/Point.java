package Item10;
// 2-1. 추이성 예제
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;
        // obj가 Point의 인스턴스가 아니라면 (상속받거나 구현한 클래스에도 객체가 포함된 것이면 true)
        Point p = (Point)obj;
        return p.x ==this.x && p.y ==this.y;
    }
    // ...
}
