package Ch2_객체생성과_파괴.Item8;

public class FinalizerExample {

    @Override
    protected void finalize() throws Throwable {    // GC 발생 시, 메서드 호출
        System.out.println("Clean up");
    }

    public void hello() {
        System.out.println("hi");
    }
}
