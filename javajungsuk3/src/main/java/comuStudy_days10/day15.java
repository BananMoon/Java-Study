package comuStudy_days10;

public class day15 {
    static class NumberThread extends Thread {
        public void run() {
            for (int i=0; i<50; i++) {
                System.out.print(i);
            }
        }
    }

    static class CharThread extends Thread {
        public void run() {
            for (char i='a'; i<='z'; i++){
                System.out.print(i);
            }
        }
    }
    public static class Test {
        public static void main(String[] args) {
            Thread thread1 = new NumberThread();
            Thread thread2 = new CharThread();

            thread1.start();
            thread2.start();
        }
    }

}
