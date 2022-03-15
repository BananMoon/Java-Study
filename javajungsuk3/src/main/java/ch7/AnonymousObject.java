package ch7;

public class AnonymousObject {
    static final int OUTER_NUM = 0;
    static int staticNum = 0;
    /**
     * 인터페이스를 구현하는 익명 객체 (static)
     */
    static MyInterface anonymousFromInterface = new MyInterface() {
        final int innerInstance = 0;
        static final int INNER_NUM = 0; // 상수
//        static int innerStatic = 0;

        // 익명 객체 내부에서는 바깥 정적변수 접근 불가
//            OUTER_NUM = 3;
//            staticNum = 3;
        public void innerMethod() {
            System.out.println("MyInterface를 구현한 익명 객체에서 새 메서드 생성!");
        }

        @Override
        public void doSomething() {
            System.out.println("MyInterface를 구현한 익명 객체에서 doSomething을 오버라이딩!");
            System.out.println(innerInstance);  // 불변 내부 변수 접근 가능
            System.out.println(staticNum);    // 외부 클래스 정적 변수 접근 가능
            System.out.println(OUTER_NUM);    // 외부 클래스 상수 접근 가능
            // 인스턴스 변수 접근 가능
        }
    };

    /**
     * 클래스를 상속하는 익명 객체(static)
     */
    static MyClass anonymousFromClass = new MyClass() {
        public void innerMethod() {
            System.out.println("MyClass를 상속한 익명 객체에서 innerMethod 생성!");
        }

        @Override
        public void doSomething() {
            System.out.println("MyClass를 상속한 익명 객체에서 doSomething을 오버로딩!");
        }
    };

    public static void main(String[] args) {
        anonymousFromInterface.doSomething();   // 접근 가능
//        anonymousFromInterface.innerMethod();   // 접근 불가능

        anonymousFromClass.doSomething();   // 접근 가능
//        anonymousFromClass.innerMethod();   // 접근 불가
//        System.out.println(INNER_NUM);  // 접근 불가
    }
}
