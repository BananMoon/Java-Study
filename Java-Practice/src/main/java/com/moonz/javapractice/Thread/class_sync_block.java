package Thread;

/**
 * synchronized로 넘기는 객체가 다른 클래스의 인스턴스일 경우, 해당 클래스의 print를 호출하는 인스턴스와는 상관없기 때문에
 * 동기화되지 않고 있다.
 */
public class class_sync_block {
    int a= 1;
    public void print(String threadName) {
        synchronized(class_sync_block.class) {
            System.out.println(threadName + " is printing...");
            try {
                Thread.sleep(1000);
                System.out.println(threadName + " is finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void run(String threadName) {
        synchronized (class_sync_block.class) {
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
