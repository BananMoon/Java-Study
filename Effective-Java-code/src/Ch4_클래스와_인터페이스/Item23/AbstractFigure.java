package Ch4_클래스와_인터페이스.Item23;

// 1) 추상 클래스
abstract class AbstractFigure {
    abstract double area();    //2) 추상 메서드 (body x)
    static void print() {         // 3) 일반 메서드
        System.out.println("정사각형 혹은 원을 그릴 수 있습니다.");
    }
}

// 2) 루트 클래스를 확장한 구체 클래스
class Circle extends AbstractFigure {
    final double radius;  // 4-1)

    Circle(double radius) {
        this.radius = radius;   // 생성자
    }
    @Override double area () { return Math.PI * (radius * radius); }     // 4-2)
}

// 2) 루트 클래스를 확장한 구체 클래스
 class Rectangle extends AbstractFigure {
    final double length;  // 4-1)
     final double width;

     Rectangle(double length, double width) {    // 생성자
         this.length= length;
         this.width = width;
     }

     @Override
     double area() { return length * width; }     // 4-2)
}
// 추가
class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}