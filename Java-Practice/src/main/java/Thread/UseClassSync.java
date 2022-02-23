package Thread;

/** synchronized 블록에 클래스를 넘겨줄 경우, 인스턴스에 상관없이 해당 블록은 동기화된다.
 * 단, 해당 클래스에 있는 다른 "메서드"에 synchronized가 붙은 경우 인스턴스 단위로 실행되고, lock이 공유되지 않는다.
 * 단2, 해당 클래스에 있는 다른 메서드에서도 synchronized 블록에 클래스를 넘겨줄 경우, 하나의 클래스 단위로 모두 공유된다.
 * <출력>
 *     thread1의 접근으로 locking 실행
 * thread1의 접근 끝나서 unlocking
 * a = 2
 * thread4 is printing...
 * thread4 is finished...
 * thread3 is printing...
 * thread3 is finished...
 * thread2의 접근으로 locking 실행
 * thread2의 접근 끝나서 unlocking
 * a = 2
 *     </출력>
 */
public class UseClassSync {
    public static void main(String[] args) {
        callSynchBlock();
    }

    private static void callSynchBlock() {
        class_sync_block csb = new class_sync_block();
        class_sync_block csb1 = new class_sync_block();

        Thread thread1 = new Thread( ()-> {
            csb.run("thread1");
        });
        Thread thread2 = new Thread( ()-> {
            csb1.run("thread2");
        });
        Thread thread3 = new Thread( ()-> {
            csb.print("thread3");
        });
        Thread thread4 = new Thread( ()-> {
            csb1.print("thread4");
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
