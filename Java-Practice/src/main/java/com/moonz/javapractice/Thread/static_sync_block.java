package Thread;

public class static_sync_block {
    static int a= 1;

    public static synchronized void print(String threadName) {
        System.out.println(threadName + " is printing...");
        try {
            Thread.sleep(1000);
            System.out.println(threadName + " is finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void run(String threadName) {
        synchronized (static_sync_block.class) {
            System.out.println(threadName + "의 접근으로 locking 실행");
            try {
                Thread.sleep(1000);
                a++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "의 접근 끝나서 unlocking ");
            System.out.println("a = " + a);
        }
    }

}
