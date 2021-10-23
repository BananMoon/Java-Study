## 동시성 문제
두 개 이상의 스레드가 공통된 자원에 대해 모두 읽고 쓰는 작업(Read+Write) 을 하려고 하는 경우 발생할 수 있는 문제<br>
<br>

### 해결 방법
여러 스레드가 동시에 한 자원에 접근할 때 한 개씩 차례대로 처리하도록 가이드해줄 지시어 `synchronized` 동기화 식별자가 필요하다.<br>
상호배제 (Mutual Exclusion) 방법을 이용한다. synchronized 키워드가 붙은 클래스는 결국 순서대로 쓰도록 만들어둔 클래스로, 한 스레드가 synchronized 클래스를 사용할 때 다른 스레드의 접근을 금지하며, 사용이 끝나면 그다음 스레드에게 바통을 넘기는 방식이다.<br>
이렇게 동기화된 클래스를 스레드에 대해 안전하다고 한다.<br>

But, synchronized 클래스를 동시에 사용하게 되면, 순차적으로 사용해야하므로 성능 저하가 발생한다.<br>
<br>

### 예시
> 1. 일반 계좌 클래스
```java
public class Account {
	long balance;
	
	public void deposit(long amount){
		balance = balance + amount;
	}
	
	public void inquiry(){
		System.out.println("잔액: " + balance);
	}
}
```

> 2. Account 클래스의 deposit(입금) 메서드를 1원씩 1000번 수행할 DepositThread 클래스
```java
public class DepositThread implements Runnable{
	Account myAccount;
	
	DepositThread(Account thisAccount){
		myAccount = thisAccount;
	}
	
	@Override
	public void run() {
		for(int i=0; i<1000; i++){
			myAccount.deposit(1);
		}
		myAccount.inquiry();
	}
}
```

> 3. DepositThread 클래스를 10번 동시에 실행시키는 스레드 WithoutSynchronizedTest 클래스
```java
public class WithoutSynchronizedTest extends Thread{

	public static void main(String[] args) {
		Account myAccount = new Account();
		
		for(int i=0; i<10 ; i++){
			Thread t = new Thread(new DepositThread(myAccount));
			t.start();
		}
	}
}
/* 결과 : 10개의 스레드의 동시 접근 문제
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
```
<br>

#### synchronized 키워드 추가
> 1-2. 입금 메소드인 deposit() 에 `synchronized` 추가
```java
public class Account {
	long balance;
	
	public synchronized void deposit(long amount){
		balance = balance + amount;
	}
	
	public void inquiry(){
		System.out.println("잔액: " + balance);
	}
}
/* 중간 잔액이 출력되는 inquiry()는 동시에 실행되므로 값이 변동이 있지만,
deposit()에 balance를 찍어보면 순차적으로 실행되는 것을 확인할 수 있다. */
```
<br>

#### synchronized 블록 추가
synchronized 지정자는 메소드 앞에서 쓰이는 것 외에 별도 블록을 지정하여 사용할 수 있다.<br>
블록을 지정할 때 공유를 제한할 대상 클래스를 명시해야한다.<br>
```java
synchronized(인스턴스명) {
  //인스턴스에 대한 키를 얻을 시, 순차적으로 실행될 코드
}
```

> 2-1. synchronized 블록 이용한 DepositThread 클래스<br>
- synchronized 블록으로 Account 계좌의 인스턴스 전체에 Lock을 걸면, 한 스레드가 1000원 입금을 완료할 때까지 다른 스레드는 기다리게 된다.<br>
```java
public class DepositThread implements Runnable{
	Account myAccount;
	
	DepositThread(Account thisAccount){
		myAccount = thisAccount;
	}
	
	@Override
	public void run() {
		synchronized(myAccount){
			for(int i=0; i<1000; i++){
				myAccount.deposit(1);
			}
			myAccount.inquiry();
		}
	}
}
/* 결과
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
```
<br>

> 참고 링크
> - https://m.post.naver.com/viewer/postView.nhn?volumeNo=8045227&memberNo=30800755
