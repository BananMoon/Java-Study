package comuStudy_days10;

import java.util.Scanner;

public class day6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=======자기소개 준비========");
        System.out.print("이름을 입력하세요: ");
        String name = sc.next();

        System.out.print("나이를 입력하세요: ");
        int num = sc.nextInt();

        System.out.print("혈액형을 입력하세요: ");
        char c = sc.next().charAt(0);

        System.out.println("=======자기소개 출력========");
        System.out.printf("안녕하세요 저는 %s 입니다. 저의 나이는 %d 살이고, \n저의 혈액형은 %c 형 입니다. 잘부탁드려요^^\n",name, num, c);
        System.out.println("=======자기소개 완료========");

        sc.close();
    }
}
