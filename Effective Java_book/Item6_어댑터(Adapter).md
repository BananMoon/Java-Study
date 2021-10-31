# 어댑터 패턴
### 한 클래스의 인터페이스를 사용하고자하는 다른 인터페이스로 변환할 때 사용하여<br> 인터페이스 호환성이 맞지 않아 쓸 수 없는 클래스를 연관 관계로 연결해서 사용할 수 있는 패턴
![image](https://user-images.githubusercontent.com/66311276/139380674-01724451-f7d3-40f6-b657-00f4bf82d235.png)

- 호환성이 없는 기존 클래스의 인터페이스를 변환하여 재사용
- 인터페이스가 바뀌더라도 그 변경 내역은 어댑터에 캡슐화되기 때문에 클라이언트는 바뀔 필요 없음.
<br>

## 어댑터 패턴이 주는 이점
- 관계가 없는 인터페이스 간 같이 사용 가능
  - 기존 클래스의 소스코드를 수정해서 인스턴스에 맞추는 작업을 할 필요 없이, 소스코드의 수정을 전혀 하지 않고 **타겟 인터페이스에 맞춰** 동작을 가능하게 한다.<br>
➡️ 즉, 기존클래스의 명세(사양)만 알면 얼마든지 새 클래스도 작성할 수 있다. <br>
- 프로그램 검사 용이
  -  소스코드가 간단해지고 유지보수도 원활하게 하는 이점
- 클래스 재활용성 증가
<br>

## 예) 110V를 220V로 변환해주는 어댑터 클래스

#1 110V에 사용되는 가전 interface, 220V에 사용되는 가전 interface
```java
public interface Elec110V {
  void powerOn110();  // 전원 키다
}

public interface Elec220V {
  void powerOn220();  // 전원 키다
}
```
<br>

#2 각 볼트(V)를 사용하는 가전 클래스 (헤어드라이기, 에어컨, 청소기)
```java
// 헤어드라이기
public class HairDryer implements Elec110V {
  @Override
  public void powerOn110V() {
    System.out.println("헤어드라이기 110V ON");
  }
}

// 에어컨
public class AirConditioner implements Elec220V {
  @Override
  public coid powerOn220V() {
    System.out.println("에어컨 220V ON");
  }
}

// 청소기
public class Cleaner implements Elec220V {
  @Override
  public void powerOn220V() {
    System.out.println("청소기 220V ON");
  }
}
```
<br>

#3 110V만 받을 수 있는 일반 가정집
```java
public class AdapterHome {
  public static void main(String[] args) {
    HairDryer hd = new HairDryer();
    connect(hd); // 헤어드라이기는 Elec110V를 상속받으니 잘 연결됨. "헤어드라이기 110V ON" 출력
    
    Cleaner cl = new Cleaner();
    connect(cl);  //  청소기는 Elec220V를 상속받기 때문에 110V를 사용할 수 없어 에러 발생.
  }
  
  public void connect(Elec110V elec110V) {  // 110V 사용하는 콘센트
    elec110V.powerOn110V();
  }
}
``` 
<br>

#4 220V를 상속받는 가전 클래스가 110V에 연결되도록 해주는 어댑터 클래스 
- **어댑터는 타겟 인터페이스(클라이언트 요구 인터페이스)를 상속받고, 호환되지 않는 다른 인터페이스를 불러 (서로 영향 받지 않고) 사용할 수 있도록 하는 패턴**
```java
public class ElecAdapter implements Elec110V {  // 타겟 인터페이스 상속
  public Elec220V elec220V;   // 호환되지 않는 다른 인터페이스의 객체 생성
  
  public ElecAdapter(Elec220V elec220V) {
    this.elec220V = elec220V;
  }
  
  @Override
  public void powerOn110V() { //110V 콘센트에 연결하는 (오버라이드된) 메서드 내부는
    elec220V.powerOn220V();   // 220V 콘센트에 연결하는 메서드를 호출 -> 110V 메서드지만 속에서 220V인스턴스로 메서드를 호출해서 변환됨! (어댑터 역할)
  }
}
```
<br>

다시 AdapterHome의 Cleaner사용 코드를 수정해보겠다.
```java
public class AdapterHome {
  public static void main(String[] args) {
    Cleaner cl = new Cleaner(); //220V 청소기
    Elec110V adapter = new ElecAdapter(cl); // cl을 110V 어댑터에 연결. 어댑터 클래스에서 변환을 해주니까 그 후에는 코드 변경할 필요 없음.
    connect(cl);  // 110V 어댑터를 110V 콘센트에 연결
  }
  
  public void connect(Elec110V elec110V) {  //110V 콘센트
    elec110V.powerOn110V();
  }
}
```
#### 220V 청소기를 110V 어댑터를 활용하여 110V 콘센트에 연결하기 성공이다.
<br> 


> 참고 : https://codedot.co.kr/7

