package ch9_일반적인_프로그래밍_원칙.Item59;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Random_Ex1 {
    // 직접 개발한 Random
    static Random rnd = new Random();
    static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
        // 0~명시한 수 사이의 난수를 양수화시킨 후, 명시한 수로 나눈다.
        // return rnd.nextInt(n);
    }

    static int threadLocalRandom(int n) {
        return ThreadLocalRandom.current().nextInt(n);
    }

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);    // 20억 /3 * 2
        int low = 0;
        for (int i=0; i< 1000000; i++)
            if (random(n) < n/2)
                low++;
        System.out.println(low);

        System.out.println("1. n이 그리 크지 않은 제곱수일 때");        // 잘 모르겠음..
        int n2 = 2 * 3;
        for (int i=0; i< 1000; i++)
            System.out.print(random(n2) + ", ");

        System.out.println("2. n이 2의 제곱수가 아닐 때(n값이 클 경우 더 두드러진다)");        // 하나도 안겹침..
        int n3 = 111111;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i< 1000; i++)
            map.put(random(n3), map.getOrDefault(random(n3), 0)+1);
       System.out.print(map.entrySet());

       // 라이브러리 Random
        int n4;
        for (int i=0; i< 1000000; i++) {
            n4 = ThreadLocalRandom.current().nextInt();
            System.out.print(n4+ ", ");
        }

    }
}
