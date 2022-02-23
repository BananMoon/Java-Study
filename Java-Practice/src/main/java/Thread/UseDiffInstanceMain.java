package Thread;


public class UseDiffInstanceMain {
    public static void main(String[] args) {
//        callSynchMethod();

        callSynchBlock();
    }

    /**
     * synchronized block도 마찬가지로 인스턴스 단위로 공유되므로, 스레드가 각자 다른 인스턴스를 생성&사용할 경우 동기화 X
     */
    private static void callSynchBlock() {
        instance_sync_block ism1 = new instance_sync_block();
        instance_sync_block ism2 = new instance_sync_block();
        Thread thread3 = new Thread( () -> {
            ism1.run("thread3");
        });
        Thread thread4 = new Thread( () -> {
            ism2.run("thread4");
        });

        thread3.start();    // 2
        thread4.start();    // 2
    }

    public static void callSynchMethod() {
        instance_sync_method ism1 = new instance_sync_method();
        instance_sync_method ism2 = new instance_sync_method();
        Thread thread3 = new Thread( () -> {
            ism1.run("thread3");
        });
        Thread thread4 = new Thread( () -> {
            ism2.run("thread4");
        });

        thread3.start();    // 2
        thread4.start();    // 2
    }
}
