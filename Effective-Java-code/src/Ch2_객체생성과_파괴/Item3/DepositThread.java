package Ch2_객체생성과_파괴.Item3;
//Account

public class DepositThread implements Runnable {
    Account myAccount;

    DepositThread(Account thisAccount) {
        myAccount = thisAccount;
    }

    @Override
    public void run() {
        // synchronized 블록으로, 스레드 수행 기간동안 인스턴스 자체에 접근 금지!
        synchronized (myAccount) {
            for (int i=0; i<1000; i++) {
                myAccount.deposit(1);
            }
            myAccount.inquiry();
        }

    }
}
