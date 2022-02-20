package com.moonz.javapractice.GugudanEx;

public class Gugudan {
    public static void main(String[] args) {
        // 1. 구구단
/*        while(true) {
            System.out.print("구구단 중 출력할 단은? ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num<2 || num > 9) {
                System.out.println("구구단은 2 이상, 9 이하의 값만 입력할 수 있습니다.");
                continue;
            }
            gugudan(num);
        }*/
        // 2. 구구단 결과를 배열에 담아서 출력
        /*while(true) {
            System.out.print("구구단 중 출력할 단은? ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num<2 || num > 9) {
                System.out.println("구구단은 2 이상, 9 이하의 값만 입력할 수 있습니다.");
                continue;
            }
            int[] ints = gugudanArray(num);
            print(ints);
        }*/

        // 3. 새로 생성한 클래스의 메서드로 수행
        GugudanMain.doGugudan();

        // 4. 요구사항 1
        GugudanMain.custom1Gugudan();

        // 5. 요구사항 2
        GugudanMain.custom2Gugudan();
    }
    private static void gugudan(int num) {
        for(int i=1; i<10; i++ )
            System.out.println(num + " x " + i + " = " + num * i);
    }
    public static int[] gugudanArray(int num) {
        int[] gugudanResult = new int[9];

        for(int i=0; i< gugudanResult.length; i++ ) {
            gugudanResult[i] = num * (i+1);
        }
        return gugudanResult;
    }

    public static void print(int[] arr) {
        for(int i=0; i<arr.length; i++)
            System.out.println(arr[i]);
    }

    public static int[] custom1GugudanArray(int num, int dan) {
        int[] customGugudanResult = new int[dan];
        System.out.println(dan +"단까지 수행하는 커스텀 " + num + "단");
        for (int i=0; i< dan; i++) {
            customGugudanResult[i] = num * (i+1);
        }
        return customGugudanResult;
    }
    public static int[] custom2GugudanArray(int dan, int multiplyNum) {
        int[] customGugudanResult = new int[multiplyNum];
        System.out.println(multiplyNum +"곱셈까지 수행하는 커스텀 " + dan + "단");
        for (int i=0; i< multiplyNum; i++) {
            customGugudanResult[i] = dan * (i+1);
        }
        return customGugudanResult;
    }
}
