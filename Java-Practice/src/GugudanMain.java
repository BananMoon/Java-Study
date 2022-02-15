import java.util.Scanner;

// 3. 새로운 클래스로 main 로직을 수행하는 새로운 클래스로 생성한다.
// 모든 로직을 한 클래스 안에서 처리하게 되면 더 많은 기능이 추가되면서 복잡해지는 경우가 생김.
public class GugudanMain {
    public static void doGugudan() {
        while (true) {
            System.out.print("구구단 중 출력할 단은? ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num < 2 || num > 9) {
                System.out.println("구구단은 2 이상, 9 이하의 값만 입력할 수 있습니다.");
                continue;
            }
            int[] ints = Gugudan.gugudanArray(num);
            Gugudan.print(ints);
        }
    }
    // 4. 최종 요구사항1
    public static void custom1Gugudan() {
        System.out.print("커스텀 구구단을 진행할 원하는 숫자를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        int dan = scanner.nextInt();
        for (int i=2; i<=dan; i++) {
            int[] oneDan = Gugudan.custom1GugudanArray(i, dan);
            Gugudan.print(oneDan);
        }
    }
    // 5. 최종 요구사항2
    public static void custom2Gugudan() {
        System.out.print("커스텀 구구단을 진행할 원하는 숫자를 (단, 곱할 최대 숫자) 형태로 입력하세요 ex) 8,7: ");
        Scanner scanner = new Scanner(System.in);
        String[] scannedStrArr = scanner.nextLine().split(",");
        int dan = Integer.parseInt(scannedStrArr[0]);
        int  multiplyNum= Integer.parseInt(scannedStrArr[1]);

        for (int i=2; i<=dan; i++) {
            int[] oneDan = Gugudan.custom2GugudanArray(i, multiplyNum);
            Gugudan.print(oneDan);
        }
    }
}