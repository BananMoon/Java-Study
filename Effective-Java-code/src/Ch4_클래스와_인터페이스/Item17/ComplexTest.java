package Ch4_클래스와_인터페이스.Item17;

public class ComplexTest {

    public static void main(String[] args) {
        /* static의 차이 */
        System.out.println("Complex.ONE의 re:  "+ Complex.ONE.re + ", Compleax.ONE의 im: " + Complex.ONE.im); // Complex.ONE의 re:  1.0, Compleax.ONE의 im: 0.0
//        System.out.println(Complex.re+"");
        // public final Double re;로 선언된 경우 : static 변수가 아니기 때문에 사용 불가. -> Non-static field 're' cannot be referenced from a static context
        // private static final Double re;로 선언된 경우 : private이기 때문에 접근불가. -> 're' has private access in 'Item17.Complex'
    }
}
