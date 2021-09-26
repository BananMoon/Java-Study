package comuStudy_days10;

import java.util.Scanner;

public class day14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        while (true) {
            System.out.print("숫자를 입력하세요: ");
            int i = sc.nextInt();

            try {
                System.out.println(value[i]);
                System.out.println("프로그램 종료");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("0~9 사이의 값을 입력하세요");
            }
        }
        sc.close();
    }
}
