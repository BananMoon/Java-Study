package Thread;

/**
 * static 메서드에 붙은 synchronized는 클래스 단위의 동기화(lock).
 *
 */
public class static_sync_method {
    static int a= 1;

    public static void print(String threadName) {
        System.out.println(threadName + " is printed");
    }
    public static synchronized void run(String threadName) {
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
