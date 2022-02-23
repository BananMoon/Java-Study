package Thread;

/**
 * 인스턴스 메서드에 붙은 synchronized는 인스턴스 단위의 동기화.
 * run 메서드에 동기화를 걸었지만, 이는 한 인스턴스를 공유하는 스레드에 대해서만 해당 자원에 대해서 동기화 처리가 되는 것이기 때문에   => UseSameInstanceMain
 * 다른 인스턴스를 생성해서 각각 run()을 호출하면 동기화되지 않으므로 synch가 맞지 않게 된다. => UseDiffInstanceMain
 */
public class instance_sync_method {
    int a= 1;

    public synchronized void print(String threadName) {
        System.out.println(threadName + " is printed");
    }
    public synchronized void run(String threadName) {
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
