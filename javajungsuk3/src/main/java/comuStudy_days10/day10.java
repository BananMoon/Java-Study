package comuStudy_days10;

import java.util.Scanner;

public class day10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("숫자를 입력하세요: ");
        int num = sc.nextInt();
        int sum1 = 0;
        for (int i=1;i<=num; i++) {
            sum1 += i;
        }
        System.out.printf("for문을 이용한 결과: %d\n",sum1);

        int sum2 = 0;
        int i = 1;
        while (i<=num) {
            sum2 += i;
            i++;
        }
        System.out.printf("while문을 이용한 결과: %d",sum2);
    }
}
