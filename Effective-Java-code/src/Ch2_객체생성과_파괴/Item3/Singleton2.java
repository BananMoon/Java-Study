package Ch2_객체생성과_파괴.Item3;

public class Singleton2 {
    public static final Singleton2 INSTANCE = new Singleton2();

    private Singleton2() {
        System.out.println("생성자 호출!!");
    }
    // 정적 팩터리 메서드
    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
