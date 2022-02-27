package Ch4_클래스와_인터페이스.Item24;

public class Outer {
    static int a = 10;
    private int b = 10;

    public void run() {
        System.out.println("Run Outer Class");

    }

    // 정적 멤버 클래스
    // 외부 클래스에 접근해야할 때는 static 중첩 클래스!
    // public or protected 클래스의 멤버 클래스라면 공개 API가 된다.
    public static class StaticInner {
        public static void run() {
            System.out.println("Run StaticInner class");
//            System.out.println(Outer.this.a);
            //  static 영역이기 때문에 nonstatic 영역에 접근 불가. 만약 a를 static으로 바꾸면 A.a는 접근 가능
            // 즉, this가 안되는 이유 : this를 사용하려면 인스턴스의 this를 사용해야하는데 static 영역에서는 인스턴스화를 하지 못하기 때문에 불가능.
        }
    }

    // 비정적 멤버 클래스
    public class NonstaticInner {
        public void run() {
            System.out.println("Run NonstaticInner");
            System.out.println("정규화된 this를 이용해서 Outer class 참조 가능 : "+ Outer.this.a);   // Outer class의 인스턴스 a에 접근 가능 (연결되있다.)
            // 비정적 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
        }
    }
}

