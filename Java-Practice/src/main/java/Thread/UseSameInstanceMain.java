package Thread;

/**
 * run 메서드에 동기화를 걸었지만, 이는 한 인스턴스를 공유하는 스레드에 대해서만 동기화 처리가 되는 것.
 *
 * 2) 만약 instance_sync_method 클래스에 synchronized가 붙지않은 메서드를 호출한다 하면?
 *  2-A)  동기화되지 않는다. synchronize가 붙지 않은 메서드는 lock이 공유되지 않는다.
 */
public class UseSameInstanceMain {
    public static void main(String[] args) {
//        callSyncMethod();

//        callSyncBlock();

        callSyncBlockWithBIns();
    }

    private static void callSyncMethod() {
        instance_sync_method ism = new instance_sync_method();
        Thread thread1 = new Thread(() -> {
            ism.run("thread1");
        });
        Thread thread2 = new Thread(() -> {
//            ism.run("thread2");
            ism.print("thread2");
        });

        thread1.start();    // 2
        thread2.start();    // 3
    }

    private static void callSyncBlock() {
        instance_sync_block isb = new instance_sync_block();
        Thread thread1 = new Thread(() -> {
            isb.run("thread1");
        });
        Thread thread2 = new Thread(() -> {
            isb.run("thread2");
//            isb.print("thread2");
        });

        thread1.start();    // 2
        thread2.start();    // 3
    }
    private static void callSyncBlockWithBIns() {
        instance_sync_block isb = new instance_sync_block();
        Thread thread3 = new Thread( () -> {
            isb.run("thread3");
        });
        Thread thread4 = new Thread( () -> {
            isb.run("thread4");
        });
        Thread thread5 = new Thread( () -> {
            isb.print("b와 상관없는 thread5");
        });
        thread3.start();    // 2
        thread4.start();    // 2
        thread5.start();
    }
}
