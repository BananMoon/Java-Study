package Ch4_클래스와_인터페이스.Item23;

public class FigureWithTag {
    public void print() {
        System.out.println("도형을 그릴 수 있습니다.");
    }   // 공통 메서드

    enum Shape { RECTANGLE, CIRCLE, SQUARE};

    // 태그 필드 : 두가지 이상(Rectangle, Circle)의 의미를 표현할 수 있으며, 현재 표현하는 의미를 나타내고자 쓰는 태그 값
    final Shape shape;  // RECTANGLE, CIRCLE이 들어올 수 있다.

    // 사각형일 때만 사용하는 데이터  필드
    double length;
    double width;

    // 원일 때만 사용하는 데이터 필드
    double radius;

    // 정사각형일 때 사용하는 데이터 필드
    double side;

    //원을 위한 생성자
    FigureWithTag(double radius) {
        shape = Shape.CIRCLE;   // enum 타입 이용
        this.radius = radius;
    }
    // 사각형을 위한 생성자
    FigureWithTag(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    // 정사각형
    FigureWithTag(int side) {
        shape = Shape.SQUARE;
        this.side = side;
    }

    // 태그 값에 따라 동작이 달라지는 메서드 -> 루트 클래스의 추상 메서드
    double area() {
        switch (shape) {
            case RECTANGLE:
                return length*width;
            case CIRCLE:
                return radius;
            case SQUARE:    
                return side*side;
            default:
                throw new AssertionError(shape);
        }
    }
}
