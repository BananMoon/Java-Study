package ch11_동시성;

import java.util.concurrent.TimeUnit;

public class volatile_안전실패 {
    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        System.out.println("generateSerialNumber nextSerialNumber : " + nextSerialNumber);
        return nextSerialNumber++;
    }
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            generateSerialNumber();
            System.out.println("backgroundThread.nextSerialNumber = " + nextSerialNumber);
        });
        Thread backgroundThread2 = new Thread(() -> {
            generateSerialNumber();
            System.out.println("backgroundThread2.nextSerialNumber = " + nextSerialNumber);
        });
        backgroundThread.start();
        backgroundThread2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(nextSerialNumber);
    }
}

// 연산자 ++는 객체에 접근하고, 값을 증가시키는 두단계로 나눠져 이루어지기 때문에
// 그 중간의 찰나의 순간에 두번째 스레드가 접근하여 값을 증가시키면, 안전 실패가 발생한다.
// volatile은 가장 최근에 기록된 값을 읽도록 보장만 할 뿐, 배타적 수행과는 상관이 없다.
// 해결 : volatile 키워드를 제거하고, generateSerialNumber()에 synchronized를 붙인다. (++배타적 수행 보장)