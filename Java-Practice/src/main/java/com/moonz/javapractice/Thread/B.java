package Thread;

public class B extends Thread{
    public synchronized void run() {
            System.out.println("B locking 실행");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B unlocking");
    }
}
