package comuStudy_days10;

import java.util.Scanner;

public class day7 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("첫번째 숫자: ");
//        int a = sc.nextInt();
//
//        System.out.print("두번째 숫자: ");
//        int b = sc.nextInt();
//
//        System.out.println("\n======사칙연산을을 해볼까요?======\n");
//        System.out.printf("%d + %d = %d\n",a,b, a+b);
//        System.out.printf("%d - %d = %d\n",a,b, a-b);
//        System.out.printf("%d * %d = %d\n",a,b, a*b);
//        System.out.printf("%d / %d = %d\n",a,b, a/b);
//        System.out.printf("%d %% %d = %d\n",a,b, a%b);

        int a = 5;
        int b = 10;

        System.out.printf("a는 %d, b는 %d 입니다.증감 연산을 해봅시다.\n",a, b);
        System.out.printf("++a와 b--의 합은 %d, a++은 %d, b는 %d", ++a + b--, a++, b);
    }
}
