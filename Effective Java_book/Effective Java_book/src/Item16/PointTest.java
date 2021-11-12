package Item16;

public class PointTest {
    public static void main(String[] args) {
        Point p1 = new Point(3, 2);
        p1.setX(2.5);
        p1.setY(3.5);
        System.out.println("x: "+ p1.getX());  // 2.5
        System.out.println("y: "+ p1.getY());  // 3.5

        TopPoint.DefaultPoint dp2 = new TopPoint.DefaultPoint();    // ok.
        dp2.getPoint1();    // p1의 x는 2.5, p1의 y는 4.5
//        TopPoint.PrivatePoint pp = new TopPoint.PrivatePoint(); -> Error. private 접근제어자로 중첩 클래스를 정의했으니 외부 클래스 외의 클래스에서 접근 불가
    }
}
