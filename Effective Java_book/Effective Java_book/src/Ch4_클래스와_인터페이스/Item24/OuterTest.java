package Ch4_클래스와_인터페이스.Item24;

public class OuterTest {
    public static void main(String[] args) {
        // 외부에서 정적 멤버변수 접근 가능
        Outer.StaticInner.run();    //  1. 정적 중첩클래스의 메서드 호출
        Outer o = new Outer();
        o.run();    // 2. 외부 클래스의 인스턴스 생성 후 메서드 호출

        // 외부에서 비정적 멤버변수 접근 불가능 : Non-static method 'run()' cannot be referenced from a static context
//        Outer.NonstaticInner.run();
        Outer.NonstaticInner ns = o.new NonstaticInner();   // 중첩 클래스의 인스턴스 생성 후에는 접근 가능!
        ns.run();   // 3.
    }
}
