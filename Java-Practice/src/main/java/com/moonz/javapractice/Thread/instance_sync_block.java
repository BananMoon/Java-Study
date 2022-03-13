package Thread;

/**
 * synchronized block은 인스턴스 단위로도, 클래스 단위로도 적용할 수 있다.
 *  lock을 거는 객체를 지정할 수 있기 때문이다. 객체를 넘기면 인스턴스 단위로 lock을 걸고 .class형식으로 넘기면 클래스 단위의 lock을 건다.
 */
public class instance_sync_block {
    int a= 1;
    B b = new B();

    public void print(String threadName) {
        System.out.println(threadName + " is printed");
    }
    public void run(String threadName) {
        synchronized (this) {
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
    public void runwithBInstance(String threadName) {
        synchronized (b) {
            System.out.println(threadName + "의 접근으로 locking 실행");
            try {
                Thread.sleep(1000);
                b.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "의 접근 끝나서 unlocking ");
            System.out.println("a = " + a);
        }
    }
}
