package Thread;

/**
 * static 메서드에 synchronized를 붙였으므로 클래스 단위로 동기화가 된다.
 *  다른 인스턴스를 생성해도 클래스 단위로 lock되므로 모두 동기화된다.
 *
 *  단, 해당 클래스 내의 synchronized 되지않은 static 메서드의 경우, 공유되지 않는다.
 */
public class UseStaticMain {
    public static void main(String[] args) {
        callStaticSyncBlock();

//        callStaticSyncMethod();
    }

    private static void callStaticSyncMethod() {
        static_sync_method ssm = new static_sync_method();
        static_sync_method ssm1 = new static_sync_method();
        Thread thread1 = new Thread(() -> {
            ssm.run("thread1");
        });
        Thread thread2 = new Thread(() -> {
//            ssm1.run("thread2");
            ssm1.print("thread2");
        });
        Thread thread3 = new Thread(() -> {
//            ssm1.run("thread2");
            ssm1.print("thread3");
        });

        thread1.start();    // 2
        thread2.start();    // 3
        thread3.start();
    }

    static void callStaticSyncBlock() {
        static_sync_block ssb = new static_sync_block();
        static_sync_block ssb1 = new static_sync_block();
        Thread thread1 = new Thread(() -> {
            ssb.run("thread1");
        });
        Thread thread2 = new Thread(() -> {
            ssb1.print("thread2");
//            ssb1.print("thread2");
        });
        Thread thread3 = new Thread(() -> {
            ssb1.print("thread3");
//            ssb1.print("thread3");
        });

        thread2.start();    // 2
        thread1.start();    // 3
        thread3.start();
    }
}
