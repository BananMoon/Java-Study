## Enum의 정의
- Enumeration의 앞 글자, "열거"라는 의미
- Java에서는 `final`을 이용해서 문자열, 숫자들을 나타내는 기본 자료형 값을 고정할 수 있는데, 이들로만 구성된 클래스는 class로 선언하지 않고, `Enum`으로 선언할 수 있다.
  - 이를 통해, 이 객체는 상수의 집합이라는 것을 명시적으로도 알수 있다.
<br><br>
## Enum의 특징
1. 클래스를 상수처럼 사용할 수 있다.
- Default 생성자는 private으로 되어있기 때문에 클래스가 로드되는 시점에서 생성되어 임의로 생성하여 사용할 수 없다. <br>
(이를 사용하고자 한다면 `EnumEx1.FIVE`와 같이 상수처럼 사용하면 된다.)
<br><br>

2. 상수 값과 같이 유일하게 하나의 인스턴스가 생성되어 사용된다.
- Enum 클래스에서 선언한 상수들은 클래스가 로드될 때 하나의 인스턴스로 생성되어 싱글톤 형태로 어플리케이션 전체에서 사용된다. 
<br><br>

3. 상속을 지원하지 않는다.
- 모든 Enum들은 내부적으로 `java.lang.enum` 클래스에 의해 상속되기 때문에, (다중 상속 지원 X) Enum은 다른 클래스를 상속받을 수 없다.
  - values(): Enum 클래스가 가지고있는 모든 인스턴스를 배열에 담아 반환
  - valueOf() : 인자로 들어온 String과 일치하는 상수 인스턴스가 존재하면 해당 인스턴스 반환 (문자열 반환 X)
  - ordinal() : Enum 클래스 내부에 있는 상수들의 index를 반환
<br><br>
## 예제  
[enum 클래스 코드]
```java
public enum EnumEx1 {
    THREE(3, 4_000),
    FOUR(4, 10_000),
    FIVE(5, 30_000);

    private final int match;
    private final int money;
    private int count;

    EnumEx1(int match, int money) {
        this.match = match;
        this.money = money;
    }

    public int getMatch() {
        return match;
    }
    public int getMoney() {
        return money;
    }
}
```
<br>
[실행 및 호출 코드]

```java
public class TestEnum {
    public static void main(String[] args) {
        // values(), ordinal() 메서드 사용
        EnumEx1[] values = EnumEx1.values();
        for (int i=0; i< values.length; i++) {
            System.out.println(values[i] +" 의 인덱스는: " + values[i].ordinal());
        }        
        // valueOf() 메서드 사용
        EnumEx1 e = EnumEx1.valueOf("THREE");
        System.out.println(e);
    }
    /* 결과
    THREE 의 인덱스는: 0
    FOUR 의 인덱스는: 1
    FIVE 의 인덱스는: 2
    THREE */
}
```
