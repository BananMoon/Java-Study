## Item10. Equals는 일반 규약을 지켜 재정의하라

## 핵심
❗ 꼭 필요한 경우가 아니라면 equals를 재정의하지 말자.<br>
❗ 재정의해야할 때는 그 클래스의 핵심 필드를 모두 빠짐없이, 다섯 가지 규약을 확실히 지켜가며 비교해야한다.
<hr>

- equals 메서드는 Object 클래스에 정의되어 있다.
  - 재정의를 하지 않게 될 경우 해당 클래스의 인스턴스는 자기자신과 같은지(**동일성**)만 평가하게 된다. 
  - 이는 재정의하기 쉬워보이지만 자칫하면 끔찍한 결과를 초래할 수 있어 다음 *열거되는 상황 중 하나에 해당한다면 재정의하지 않는 것이 최선*이다.
![image](https://user-images.githubusercontent.com/66311276/139585383-a365e43f-cdab-4b2d-b460-268ec77b31a8.png)

- 두개의 인스턴스는 논리적으로는 같은 문자열이지만 다른 인스턴스 변수에 저장되어있는데, <br> String 클래스는 `equals()`메서드를 **재정의**하여 객체들의 값을 비교한다.<br>
```java
String s1 = new String("java");
String s2 = new String("java");
System.out.println(s1.equals(s2));  //true
```
<hr>

### 📌재정의하지 않아도 되는 경우
**1. 각 인스턴스가 본질적으로 고유하다.**
- 값(필드)이 아닌 **동작하는 개체**를 표현하는 클래스
- 예) Thread, StringBuilder(String을 가변 배열에 담아 메모리 절약, 조작 용이하게 도와주는 클래스)

```java
String s1 = "ten";
String s2 = "ten"; 
String s3 = new String("ten");
String s4 = new String("ten");
StringBuilder sb1 = new StringBuilder("ten");
StringBuilder sb2 = new StringBuilder("ten");

// 비교
// 1) s1.equals(s2) : true. 두 객체는 서로 동일(same)하고 동등(equal)하다.
// 2) s2.equals(s3) : true. 두 객체는 서로 동일하지 않지만(Not same) 동등(equal)하다.
// 3) s3.equals(s4) : true. 두 객체는 서로 동일하지 않지만(Not same) 동등(equal)하다.
// 4) sb1.equals(sb2) : false  (StringBuilder는 equals()를 재정의하지 않아서 동일성(주소값)만 비교)
```
<br>

**2. 인스턴스의 논리적 동치성을 검사할 일이 없다.**<br>
❓논리적 동치성(Logical equality)❓
- 두개의 오브젝트가 같은 정보를 갖고 있는가 
- 동등성(Equality) 비교
- 물리적 동치성 == 객체 식별성 (Object identity) : 객체의 참조값이 서로 같은가 (동일성)

> 참고
> 1. 동일성(Identity) : 두개의 오브젝트가 완전히 같은 오브젝트인가?
> 2. `==` 연산자
>   - primitive 타입의 자료형(byte, short, int, long, float, double, boolean) : 데이터 값을 비교(동등성 비교)
>   - reference 타입의 자료형(클래스의 오브젝트를 생성해서 메모리에 올리고, 그 참조 변수를 반환해주는 연산자인 `new`를 통해 생성된 타입) : 값 비교 시 2가지 의미
>     1. `==` 연산자 사용 시, 주소값(동일성) 비교
>     2. `equals()` 메서드 사용 시, 내용(동등성) 비교<br>
> 2-1. 예시
```java
int a = 10;
int b = 10;
String s1 = "ten";
String s2 = "ten";
String s3 = new String("ten");
StringBuilder sb1 = new StringBuilder("ten");
StringBuilder sb2 = new StringBuilder("ten");

// 비교
// 1) a==b : true
// 2) s1==s2 : true
// 3) s2==s3 : false (주소값 다름)
// 4) sb1==sb2 : false (주소값 다름)
```
- 리터럴("") 방식으로 생성 시, constant pool 영역으로 찾아가서 이전과 같은 값을 가진 String 객체(s3)가 있다면 그 객체의 주소값 반환하여 참조.
![image](https://user-images.githubusercontent.com/66311276/140585458-167de4ed-fce4-4ea1-b92f-11f595887883.png)
[출처](https://javanitto.tistory.com/9)
<br>

**3. 상위 클래스에서 재정의한 equals가 하위 클래스에서 들어맞는다.**
- 상위에서 구현한 equals로직으로 충분한 경우 재정의 X, 상위클래스에 정의된 equals를 오버라이드
- Set은 AbstractSet이 구현한 equals를 상속
<br>

**4. 클래스의 접근 제어자가 `private`이거나 `package-private`(default)이고 equals()를 호출할 일이 없다.**
- equals를 재정의해줄 필요가 없다.
- 이에 더해, equals가 실수로라도 호출되는 걸 막고 싶다면 다음과 같이 재정의한다.
```java
@Override
public boolean equals(Object o) {
  throw new AssertionError();   // equals() 호출 시 Error를 던진다.
}
```
<hr>

## 📌재정의가 필요한 경우
**1. 객체 식별성이 아니라 논리적 동치성을 확인해야하는 경우**
- 논리적 동치성 비교 : 핵심 값을 비교하여 두 객체가 서로 동등한지 비교
- 상위클래스의 `equals()`에서 논리적 동치성 (logical equality)을 비교하도록 재정의하지 않은 경우<br>
ex) 값 클래스 : 값을 표현하는 클래스 (Integer, String)

- But! 값 클래스여도 값이 같은 인스턴스가 2개 이상 만들어 지지 않도록 보장하는 **인스턴스 통제 클래스**일 경우, 재정의하지 않아도 된다. (Item 1의 정적 팩터리 메서드 장점 #2, `Enum`)<br>
➡️ 어차피 논리적으로 같은 인스턴스가 2개 이상 만들어지지 않으니 논리적 동치성과 객체 식별성이 사실상 똑같은 의미가 됨.
<hr>

## 📌재정의할 때 따라야하는 규약 (Object 명세에 적힌 규약)
**1) 반사성 (reflextivity)**
- null이 아닌 모든 참조값 x에 대해, `x.equals(x)`는 true이다.
- 객체는 자기 자신과 같아야 한다는 뜻
- 예) `"moon".equals("moon")` <br>
➡️ 이 조건은 만족하지 못하도록 하는게 더 힘들 듯함
<br>

**2) 대칭성 (Symmetry)**
- null이 아닌 모든 참조 값 x, y에 대해 **`x.equals(y)`가 true면 `y.equals(x)`도 true다.** (결과가 같아야 한다.)
- 두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다는 뜻
- [해당 예제](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Effective%20Java_book/src/Item10/CaseInsensitiveString.java) 참고
<br>

**3) 추이성 (Transitivity)**
- null이 아닌 모든 참조 값 x, y, z에 대해 **`x.equals(y)`가 true이고 `y.equals(z)`도 true면 `x.equals(z)`도 true다.**
- 해당 예제 참고
  - [Point 클래스](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Effective%20Java_book/src/Item10/Point.java)
  - [ColorPoint 클래스](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Effective%20Java_book/src/Item10/ColorPoint.java)
- 추이성 문제와 무한 재귀 문제에 빠질 위험까지 있다.
  - 이를 해결하고자 instanceof 검사 대신 getClass 검사를 사용한다? **해결하지 못한다.**
  - 이는 리스코프 치환 원칙을 위배한다. 
> 리스코프 치환 원칙이란,
> 어떤 타입에 있어 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다. 따라서 그 타입의 모든 메서드가 하위 타입에서도 똑같이 잘 작동해야한다.

<br>

**4) 일관성 (Consistency)**
- null이 아닌 모든 참조 값 x, y에 대해 **`x.equals(y)`를 반복 호출하면 항상 true or 항상 false를 반환한다.**
- 가변 객체는 비교 시점에 따라 서로 다를 수 있지만
- 불변 객체는 한번 다르면 끝까지 달라야 한다. (영원히)
- **클래스가 불변이든 가변이든, `equals`의 판단에 신뢰할 수 없는 자원은 끼어들면 안된다.**
- 예) `java.net.URL`의 equals는 주어진 URL과 매핑된 호스트의 IP주소를 비교한다.
   - 호스트 이름을 IP주소로 바꾼 결과가 항상 같다고 보장할 수 없다.
- 대책 : equals는 항상 메모리에 존재하는 객체만을 사용한 **결정적(deterministic) 계산만 수행해야한다.
<br>

**5) null-아님**
- null이 아닌 모든 참조 값 x에 대해 **`x.equals(null)`은 false다.**
- 즉, 모든 객체가 null과 같지 않아야 한다는 뜻
- 이를 위해 수많은 클래스가 아래 코드처럼 *명시적 null검사*를 통해 확인한다. (NullPointerException을 던지거나 등등)
```java
@Override public boolean equals(Object o){
  if( o == null){
    return false;
  }
}
```
<br>

- 이러한 검사는 필요 X
- 동치성을 검사하려면 건네 받은 객체를 적절히 형변환 후 필수 필드들의 값을 알아내야 한다. <br>이를 위해 `instanceof` 연산자로 올바른 타입인지 검사하는데 이때 입력이 null이면 (타입 확인 단계에서) false를 반환하므로 명시적으로 검사할 필요 X.

```java
@Override public boolean equals(Obejct o){
  if(!(o instanceof MyType)) 	// instanceof 자체가 타입과 무관하게 null이면 false 반환함.
    return false;
  MyType mt = (MyType) o;
}
```
<hr>
<br>

### 📌equals() 메서드 구현 방법의 단계별 정리 (종합)<br>
**1) == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.**
  - object identity를 검사한다.<br>
**2) instanceof 연산자로 입력이 올바른 타입인지 확인한다.*
  - 그렇지 않다면 false 반환<br>
**3) 입력을 올바른 타입으로 형변환한다.**
  - 2번에서 instanceof 검사를 했으므로 100% 성공<br>
**4) 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.*
<br>

> 이를 잘 지켜 재정의한 equals() 예제
```java
public class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }
    
    private static short rangeCheck( int val, int max, String arg) {
        if (val < 0 || val > max) throw new IllegalArgumentException(arg + ": " + val);
        return (short)val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)  // 1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
            return true;
        if (!(obj instanceof PhoneNumber))   // 2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
            return false;
        PhoneNumber pn = (PhoneNumber) obj;  // 3. 입력을 올바른 타입으로 형변환한다. (Object 타입으로 들어왔으므로 비교를 위해 형변환)
        return pn.lineNum == this.lineNum && pn.prefix == this.prefix 
                && pn.areaCode == this.areaCode;    // 4. 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.
    }
    //...
}
```

> equals를 재정의했다면 3가지를 CHECK하자!
- 대칭적?
- 추이성?
- 일관성?

<hr>
<br>

### 📌마지막 주의사항 3가지
1) equals 재정의 시 hashCode도 반드시 재정의한다.
2) 너무 복잡하게 해결하려하지마라.
   - 일반적으로 별칭(alias)는 비교하지않는게 좋다.
3) Object.equals 메서드의 매개변수가 Object 타입이었으므로 오버라이딩을 하기위해 똑같이 맞춰줘야 한다.
  -> Object 외의 타입을 매개변수로 받으면, 재정의(오버라이딩)가 아닌 다중정의(오버로딩)가 됨.
```java
ublic boolean equals(MyClass o) { ... }  //매개변수까지 Object로 같아야 재정의. 이건 오버로딩!
```

> 해당 글 관련 예제 전체 링크 : https://github.com/BananMoon/Java-Study/pull/2
