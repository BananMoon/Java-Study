### 구구단 출력하기 (Scanner 활용)
- Scanner를 활용해서 2 이상 9 이하의 숫자만 입력받는다. (조건문)
- 반복문을 통해 출력한다.

```java
public class Gugudan {
    public static void main(String[] args) {
        // 1. 구구단
        while (true) {
            System.out.print("구구단 중 출력할 단은? ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num < 2 || num > 9) {
                System.out.println("구구단은 2 이상, 9 이하의 값만 입력할 수 있습니다.");
                continue;
            }
            gugudan(num);
        }
    }
    private static void gugudan(int num) {
        for(int i=1; i<10; i++ )
            System.out.println(num + " x " + i + " = " + num * i);
    }
}
```
<br>

### 구구단 출력하기 (배열 활용)
- 계산된 값을 배열에 추가한다.
- 각 기능을 하는 메서드로 추출한다.

```java
public class Gugudan {
    public static void main(String[] args) {
        while(true) {
            System.out.print("구구단 중 출력할 단은? ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num<2 || num > 9) {
                System.out.println("구구단은 2 이상, 9 이하의 값만 입력할 수 있습니다.");
                continue;
            }
            int[] ints = gugudanArray(num);
            print(ints);
        }
    }
    private static int[] gugudanArray(int num) {
        int[] gugudanResult = new int[9];

        for(int i=0; i< gugudanResult.length; i++ ) {
            gugudanResult[i] = num * (i+1);
        }
        return gugudanResult;
    }
    private static void print(int[] arr) {
        for(int i=0; i<arr.length; i++)
            System.out.println(arr[i]);
    }
} 
```
<br>

### 구구단 출력하기 (클래스 활용)
- 기존처럼 모든 로직을 한 클래스 안에서 처리하게 되면 다양한 기능(덧셈, 뺄셈 등..)이 추가되면서 복잡해지는 경우가 생긴다.
- 구구단 메서드를 이용하는 main 메서드를 새로운 클래스 GugudanMain으로 만든다.
- Gugudan에서 선언해준 private static 메서드들은 public으로 바꿔주어야 GugudanMain에서 접근가능하다.
```java
import java.util.Scanner;

// 3. 새로운 클래스로 main 로직을 수행하는 새로운 클래스로 생성한다.
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
}
```
<br>

### 최종 요구사항 1
- 사용자가 입력한 값에 따라 크기가 다른 구구단을 계산해 출력한다.
- 예를 들어 사용자가 8을 입력하면 팔팔단, 19를 입력하면 십구십구단(2 * 1에서 19 * 19)을 계산해 출력한다.
- 코드 : `GugudanMain 클래스의 custom1Gugudan 메서드` -> `Gugudan의 custom1GugudanArray 메서드` 호출

### 최종 요구사항 2
- 사용자가 입력한 값에 따라 크기가 다른 구구단을 계산해 출력한다. 
- 예를 들어 사용자가 "8,7"과 같은 문자열을 입력하면 팔칠단을 구현한다. 팔칠단은 2 * 1 ... 2 * 7, 3 * 1 ... 3 * 7, ... , 8 * 1 ... 8 * 7 까지 구현하는 것을 의미한다.
- 코드 : `GugudanMain 클래스의 custom2Gugudan 메서드` -> `Gugudan의 custom2GugudanArray 메서드` 호출
