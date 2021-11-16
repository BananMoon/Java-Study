package Ch2_객체생성과_파괴.Item3;

public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1() {
        System.out.println("생성자 호출!!");
    }
}
