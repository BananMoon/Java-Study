package Item3;
// 동시성문제 해결 못한 (Synchronized 없이) 10개의 멀티 스레드 동작시킴
public class WithoutSynchronizedTest extends Thread{
    public static void main(String[] args) {
        Account myAccount = new Account();

        for (int i=0; i<10; i++) {
            Thread t = new Thread(new DepositThread(myAccount));
            t.start();
        }
    }
}

/*결과
잔액: 7000
        잔액: 8939
        잔액: 3000
        잔액: 5000
        잔액: 8027
        잔액: 6000
        잔액: 4000
        잔액: 2000
        잔액: 1000
        잔액: 9939
*/

/* synchronized 블록 결과
잔액: 1000
잔액: 2000
잔액: 3000
잔액: 4000
잔액: 5000
잔액: 6000
잔액: 7000
잔액: 8000
잔액: 9000
잔액: 10000
 */