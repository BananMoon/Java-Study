package Ch4_클래스와_인터페이스.Item23;

public class 계층구조Test {
    public static void main(String[] args) {
        System.out.println("태그 달린 클래스 테스트==========");

        FigureWithTag what1 = new FigureWithTag(2.5);
        FigureWithTag what2 = new FigureWithTag(2.5, 2.5);

        what1.print();
        what2.print();
        System.out.println("원 : " + what1.area());
        System.out.println("사각형 : " + what2.area());

        System.out.println("계층구조 클래스 테스트==========");

        Circle circle = new Circle(1.5);
        Rectangle rectangle = new Rectangle(1.5,1.5);
        Circle.print();
        Rectangle.print();
        System.out.println("원 : " + circle.area());
        System.out.println("사각형 : " + rectangle.area());
        // 추가
        Square square = new Square(4);
        System.out.println("정사각형 : " + square.area());
    }
}
