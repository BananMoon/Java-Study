package Item3;

public class Singleton7 {

    private Singleton7() {
        System.out.println("생성자 호출!!");
    }
    /**
     * static member class
     * static 멤버, 특히 static 메서드에서 사용될 목적으로 선언
     */

    private static class InnerInstanceClazz {
        // static: 클래스 로딩 시점에서 생성 -> 수정 불가능
        private static final Singleton7 uniqueInstance = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return InnerInstanceClazz.uniqueInstance;
        // 내부클래스를 호출(이 시점에 생성)해서 반환
    }
}
