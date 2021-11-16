package Ch4_클래스와_인터페이스.Item16;

public class TopPoint {
    // package-private 중첩 클래스 : 패키지 내부까지 제안
    static class DefaultPoint {
        public double x;    // 데이터 필드 노출
        public double y;

        public static void getPoint1() {    //  필드 노출
            DefaultPoint dp1 = new DefaultPoint();
            dp1.x = 2.5;
            dp1.y = 4.5;
            System.out.println("dp1의 x는 "+ dp1.x+ ", dp1의 y는 "+ dp1.y);
        }
    }

    // private 중첩 클래스 : 이 클래스를 포함하는 외부 클래스까지로 제한.
    private static class PrivatePoint {
        public double x;       // 데이터 필드 노출
        public double y;

        public static void getPoint2() {    //  필드 노출
            PrivatePoint pp1 = new PrivatePoint();
            pp1.x = 3.0;
            pp1.y = 6.0;
            System.out.println("pp1의 x는 "+ pp1.x+ ", pp1의 y는 "+ pp1.y);
        }
    }
    public static void main(String[] args) {
        DefaultPoint.getPoint1();       // ok.
        PrivatePoint.getPoint2();       // ok. 외부 클래스인 TopPoint에서는 private 중첩 클래스에 접근 가능
    }
}

