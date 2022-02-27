package Ch4_클래스와_인터페이스.Item25;

public class Main {
    // 한 파일에 여러 개의 클래스를 정의하지말고
    // 1. 여러 파일로 분할한다.
    // 2. 한 파일 안에 담고 싶다면, 하나의 클래스 아래에 정적  멤버 클래스로 감싼다.
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    // private 선언 시 접근범위도 최소화 가능.
    private static class Utensil {
        static final String NAME = "pen";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }
}
