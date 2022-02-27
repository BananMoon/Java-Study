package Ch2_객체생성과_파괴.Item3;
// singleton의 동시성 문제를 해결하는 예제
public class Account {
    long balance;

    // 1번째 방법) synchronized 키워드 추가 -> inquiry()는 동시 접근 가능...
    public synchronized void deposit(long amount){
        balance = balance + amount;
//        System.out.println("deposit에서 "+ balance);
    }

    public void inquiry(){
        System.out.println("잔액: " + balance);
    }
}