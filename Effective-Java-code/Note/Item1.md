# 2장. 객체 생성과 파괴
2장을 공부하고 나면,
객체를 만들어야 할 때와 만들지 말아야 할 떄를 구분하는 법,
올바른 객체 생성 방법과 불필요한 객체 생성을 피하는 방법,
제때 파괴됨을 보장하고 파괴 전에 수행해야 할 정리 작업을 관리하는 요령
을 알수 있게 된다.

## Item 1. 생성자 대신 정적 팩터리 메서드를 고려하라.
클라이언트가 클래스의 인스턴스를 얻는 수단
1. public 생성자
2. 정적 팩터리 메서드 (static factory method)

### 정적 팩터리 메서드의 장점<br>
**#1 이름을 가질 수 있다.** <br>
반환되는 객체의 특성을 잘 묘사할 수 있게 된다.<br>
만약 시그니처가 같은 생성자가 여러개 필요한 상황이라면, 생성자를 정적 팩터리 메서드로 바꾸고 각각 차이를 드러내는 이름으로 지어주면 된다.
```java
class House{
  private String name;
  private int roomN;
  
  // 생성자
  public House(String name, int roomN) {
    this.name = name;
    this.roomN = roomN;
  }
  
  // 정적 팩토리 메서드를 이용한 명시적 이름
  public static House getFirst() {
    House house = new House();
    house.name = "First";
    house.roomN = 1;
    return house;
  }
  
  public static House getSecond() {
    House house = new House();
    house.name = "Second";
    house.roomN = 2;
    return house;
  }
}
```
**#2 호출될 때마다 인스턴스를 새로 생성하지는 않아도된다.**<br>
- 특히 불변 클래스(immutable class)처럼 인스턴스를 미리 만들어놓거나 새로 생성한 인스턴스를 캐싱해서 전달하는 식으로 불필요한 객체 생성을 피할 수 있는데, 정적 팩터리 메서드를 활용하면 가능해진다.<br>
`Boolean.valueOf(boolean)` 메서드를 예로 들 수 있다.

- 인스턴스를 새로 만들지 않고 새로 생성한 인스턴스를 캐싱해서 재활용할 수 있어 **불필요한 객체 생성을 피할 수 있다.**
  - 생성 비용이 크면서 동일한 객체를 자주 요청하는 상황이라면, 성능을 많이 끌어올릴 수 있다.
  - Flyweight pattern도 비슷한 기법이다.
- 이렇게 반복되는 요청에 같은 객체를 반환하는 식의 정적 팩터리 메서드를 사용하면, 언제 어느 인스턴스를 살아 있게 할지를 철저히 통제할 수 있다. -> 인스턴스 통제(instance-contorlled) 클래스


> 인스턴스를 통제하는 이유는?
> 1. 싱클턴으로 생성 가능
> 2. 인스턴스화 불가(noninstantiable)로 가능
> 3. 불변 값 클래스에서 동치인 인스턴스가 단 하나뿐임을 보장 가능

### -> 이부분 이해 필요

**#3. 반환 타입의 하위타입 객체를 반환할 수 있는 능력이 있다.**<br>
- 이 능력은 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 유연성을 제공한다.
- API를 만들 때 이 유연성을 응용하여 구현 클래스를 공개하지 않고도 그 객체를 반환할 수 있어 API를 작게 유지할 수 있다.<br>
➡️ 인터페이스를 정적 팩터리 메서드의 반환 타입으로 사용하는 인터페이스 기반 프레임워크를 만드는 핵심 기술

```java
class WeaponUtil {
  public static Weapon getWeapon(int damage) throws Exception {
    if (0 <= damage && damage <= 50) {
      return new Sword(); //하위타입 객체 반환
    } else if (50 < damage && damage <= 100) {
      return new Gun();
    }
    throw new Exception("There is no weapon");
  }
}

class Weapon{ }
class Sword extends Weapon { }
class Gun extends Weapon { }
```

**#4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.**
- 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관 없다.
- 클라이언트는 팩터리가 건네주는 객체가 어느 클래스의 인스턴스인지 알 수도, 알 필요도 없다.
<br>

**#5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.**


## 참고 사항
#### 박싱 클래스<br>
`Lisst<String> list = new ArrayList<>();`<br>
위와 같은 제네릭 타임에는 기본 자료형인 원시형(Primitive Type)은 넣지 못한다. <br>
제네릭 타입에 넣으려면 원시형을 담아내는 클래스를 넣어야한다.<br>
**즉, 원시형을 객체화한 래퍼 클래스 (Wrapper Class)를 넣어야한다.**
| 래퍼 클래스 종류 |
|:-:|
|Boolean|
|Character|
|Double|
|Float|
|Integer|
|Long|
|Short|
|Byte|

**박싱(Boxing)** : 원시형을 래퍼 클래스로 바꾸는 방법
```java
Integer x = new Inter(3); //기본형 3을 래퍼클래스로 박싱
Integer x = 3;  //Auto-Boxing
```

**언박싱(Unboxing)** : 래퍼 클래스를 원시형으로 바꾸는 방법
```java
// 형식 : {기본형} {변수} = {래퍼클래스 변수}.{기본형}Value();
Integer x = new Integer(3); //박싱
int x2 = x.intValue();  //언박싱
int x2 = x; //Auto-Unboxing
```
> 원시형과 래퍼 클래스 둘다 사용할 수 있는 경우라면, 메모리를 덜 차지하는 원시형을 사용하자


레퍼런스
- https://cbw1030.tistory.com/285
