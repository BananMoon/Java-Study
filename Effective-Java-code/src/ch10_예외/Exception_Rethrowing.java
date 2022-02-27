package ch10_예외;

public class Exception_Rethrowing {
    public static void main(String[] args) {
        A();
    }

    static void A () {
        try {
            B();
        } catch (Exception e) {
            System.out.println("메서드 A에서도 예외 처리");
        }
    }

    static void B() throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("메서드 B에서 예외 처리");
            // 특정 예외에 대해 이를 호출한 메서드에서도 예외처리를 해주어야 할 때 예외를 인위적으로 다시 발생시킨다.
            // 선언부에 추가해야 한다.
            throw e;
        }
    }
}
