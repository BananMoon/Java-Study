# Item3. private 생성자나 열거 타입으로 싱글턴임을 보증하라.

## 1. 싱글턴 (Singleton)
- 인스턴스가 오직 1개만 생성되어야하는 경우 사용되는 패턴 (디자인 패턴 중 하나)
- 하나의 인스턴스를 메모리에 등록 -> 여러 스레드가 동시에 해당 인스턴스를 공유하여 사용할 수 있음 -> 요청이 많은 곳에서 효율성↑
- 하나의 최초 생성 이후에 호출된 생성자는 최초에 생성된 객체를 반환해준다.
- 주의해야할 점 : **동시성(Concurrency) 문제** 고려해서 싱글턴 설계해야 함<br>
  (➡️[동시성 문제 설명](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Item3_%EB%8F%99%EC%8B%9C%EC%84%B1%EB%AC%B8%EC%A0%9C.md) 참고)
- 장점
  - 고정된 메모리 영역을 얻음으로써 메모리 낭비를 방지할 수 있다.
  - 데이터를 공유하기 쉽다. (public static으로 생성된 인스턴스)
- 문제점
  - 싱글톤 인스턴스가 너무 많은 일을 하거나 많은 데이터를 공유시킬 경우 다른 클래스의 인스턴스들 간에 결합도가 높아져 "개방-폐쇄 원칙" 을 위배할 수 있다.
  - 따라서 수정이 어려워지고, 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다.   (➡️가짜(mock) 구현으로 대체)

<br>

## 2.단일 스레드 환경에서 싱글턴 패턴 구현
> (1), (2) 방법 모두 생성자를 `private`으로 감춰두고, 유일한 인스턴스에 접근할 수 있는 수단을 제공한다.
> - `public static final` 필드로 선언되는 인스턴스 제공
> - 정적 팩터리 메서드를 public static 멤버로 제공<br>
> 
> 대부분 상황에서의 가장 좋은 방법은 (3) 열거 타입 방식의 싱글턴 이다. But 현실적으로 사용X

### (1) 하나의 public static final 필드를 생성하는 방식의 싱글턴
```java
public class Singleton1 {
  public static final Singleton1 INSTANCE = new Singleton1();
  private Singleton1() {
    System.out.println("생성자 호출");
  }
}
```
➡️ private 생성자는 public static final 필드 Singleton1.INSTANCE를 초기화할 때 딱 한번 호출<br>
➡️ Singleton1 클래스가 초기화될 때 만들어진 인스턴스가 전체 시스템에서 하나뿐임을 보장<br>
➡️ 장점 : 1. 해당 클래스가 싱글턴임이 API에 명백히 드러난다.(`public static` 필드가 `final`)   2. 간결함
<br>
> **예외!!!!** 권한이 있는 클라이언트는 리플렉션 API인 `AccessibleObject.setAccessible`로 private 생성자를 호출할 수 있다.
```java
@Test
@DisplayName("리플렉션을 사용하면 private 생성자를 호출할 수 있다.")
public void reflection() throws Exception {
    Singleton1 singleton1 = Singleton1.INSTANCE;
    Singleton1 singleton1Reflection;

    Constructor<Singleton1> constructor = Singleton1.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    singleton1Reflection = constructor.newInstance();
    
    Assertions.assertNotSame(singleton1, singleton1Reflection);
}
```
➡️ 대응책 : 생성자에서 두번 째 객체가 생성되려할 시 예외를 던진다.
<br>

> 테스트 환경 참고)<br>
> Test 디렉토리 지정 : https://ildann.tistory.com/5
> JUnit5에 대하여 : https://sabarada.tistory.com/79
<br>

### (2) 정적 팩터리 메서드를 public static 멤버로 제공하는 방식의 싱글턴
```
public class Singleton2 {
    public static final Singleton2 INSTANCE = new Singleton2();
    
    private Singleton2() {
        System.out.println("생성자 호출!!");
    }
    // 정적 팩터리 메서드
    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
```
➡️ 위의 테스트 코드 Singleton2.java 작성
```java
@Test
@DisplayName("인스턴스가 전체 시스템에서 하나뿐임을 보장한다.")
class Singleton1Test {
    Singleton1 instance1 = Singleton1.INSTANCE;
    Singleton1 instance2 = Singleton1.INSTANCE;

    //단정(assert) 메소드: 테스트 케이스의 수행 결과를 판별
    //assertSame(ox, oy): ox와 oy가 같은 객체를 참조하고 있으면(레퍼런스 동일?) 테스트 통과
    Assertions.assertSame(instance1, instance2);
}
```
➡️ Singleton2.getInstance()는 항상 같은 객체의 참조를 반환하므로 제 2의 Singleton2 인스턴스는 만들어 지지 않는다. (리플렉션 예외 필요)<br>
➡️ 정적 팩터리 메서드의 장점
  1. API를 바꾸지 않고도 싱글턴이 아니게 변경 가능<br>
    (유일한 인스턴스를 반환하던 팩터리 메소드가 호출하는 스레드별로 다른 인스턴스를 넘겨주게 할 수 있다. ) <br>
  2. 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.
  3. 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용할 수 있다.

### (3) 열거 타입 방식의 싱글턴

➡️ 아래의 **4. Lazy Initialization.Enum (열거 상수 클래스, Thread-safe)** 를 참고<br><br>
<hr>

## 3. 멀티 스레드 환경에서 안전한 (Thread-safe) 싱글턴 패턴 구현 
> **Thread-safe**란?
> - 스레드 안전(thread safety)은 **멀티 스레드 프로그래밍**에서 일반적으로 어떤 함수나 변수, 혹은 객체가 **여러 스레드로부터 동시에 접근**이 이루어져도 **프로그램의 실행에 문제가 없음**을 뜻한다.
> - 보다 엄밀하게는 하나의 함수가 한 스레드로부터 호출되어 실행 중일 때, 다른 스레드가 그 함수를 호출하여 동시에 함께 실행되더라도 **각 스레드에서의 함수의 수행 결과가 올바로 나오는 것**으로 정의한다.

> 아래 방식 다섯가지는 싱글턴 패턴 구현에 사용되는 몇가지 이디엄 방식이다.
### 1. Eager Initialization (이른 초기화, **Thread-safe**)
- ❓ `static` 키워드의 특징을 이용
- ❓ 클래스 로더가 초기화하는 시점에 (컴파일 시점에 성격이 결정되는)정적 바인딩을 통해 인스턴스를 메모리에 등록해서 사용하는 것
- 클래스 로더(라이브러리를 위치시키고 내용물을 읽고 라이브러리들 안에 포함된 클래스들을 읽는 역할. 자바 클래스를 로드하는 역할)에 의해 클래스가 최초로 로딩될 때 객체가 생성되기 때문에 **Thread-safe**하다.
- 싱글턴 구현 시 중요한 점! 멀티 스레딩 환경에서도 동작 가능하게 구현, 즉 Thread-safe가 보장되어야 한다.<br>

<예시> 아래 Singleton3 클래스는 로드될 때 한개의 인스턴스 uniqueInstance가 생성되는 것을 알 수 있다.
```java
public class Singleton3 {
    // Eager Initialization
    private static Singleton3 uniqueInstance = new Singleton3();

    private Singleton() {}

    public static Singleton3 getInstance() {
      return uniqueInstance; 
    } 
}
```
<hr>

### 2. Lazy Initialization with synchronized (동기화 블럭, Thread-safe) : `synchronized` 키워드를 이용한 게으른 초기화 방식
- ❓ 게으른 초기화 방식이란, 컴파일 시점에 인스턴스를 생성 ✖️, **인스턴스가 필요한 시점에 요청**하여 동적 바인딩(런타임 시에 성격 결정)을 통해 인스턴스를 생성하는 방식이다.
- 메서드에 동기화 키워드를 부여함으로써 Thread-safe를 보장한다.
```java
public class Singleton4 {
    private static Singleton4 uniqueInstance;

    private Singleton4() {}

    // Lazy Initailization
    public static synchronzied Singleton4 getInstance() {
      if(uniqueInstance == null) {
         uniqueInstance = new Singleton4();
      }
      return uniqueInstance;
    }
}
```
- But, 동기화 키워드를 부여함으로써, <br>
  인스턴스가 생성됐든, 안됐든 무조건 동기화가 이루어지기 때문에 성능이 매우 떨어진다. (synchronized 키워드 사용시 문제점)
- 이를 개선한 방식이 아래 방식.
<hr>

### 3. Lazy Initialization.Double Checking Locking (DCL, Thrad-safe)
- ❓ **인스턴스가 생성되지 않은 경우에만 동기화 블록이 실행되게끔 구현**하는 방식
- 멀티 스레드 환경에서 여러개의 스레드가 write하는 상황에는 동기화 블록을 지정해서 원자성을 보장해야 한다.
```java
public class Singleton5 {
    private volatile static Singleton5 uniqueInstance;

    private Sigleton5() {}

    // Lazy Initialization. DCL
    public Singleton5 getInstance() {
      if(uniqueInstance == null) {
         synchronized(Singleton5.class) {    // uniqueInstance가 아직 생성되지 않은 경우에만, 클래스 자체에 동시성 블록을 부여해서 멀티 스레드 환경에서의 동시성 문제 해결!
            if(uniqueInstance == null) {
               uniqueInstance = new Singleton5(); 
            }
         }
      }
      return uniqueInstance;
    }
}
```
<br>

> `volatiole` 키워드가 왜 필요할까?<br>
> - 멀티 스레드가 동작중일 때는 성능 향상을 위해 메인 메모리에서 읽은 변수 값을 CPU Cache(메모리에서 가장 자주 사용되는 위치의 데이터를 갖고 있는, 크기는 작지만 빠른 메모)에 저장하게 된다. 그리고 이를 읽어오는 것이 효율적인 방법이다.
> - 때문에 멀티 스레드 환경에서 스레드가 변경한 값이 메인 메모리에 저장되지 않아서 다른 스레드가 이 값을 볼 수 없는 상황(가시성 문제) 발생한다.수 값을 읽어올 때 각각의 CPU Cache에 저장된 값이 다를 때 변수 값 불일치 문제가 발생한다.<br>
> ➡️`volatile`키워드를 선언한다면 컴파일러의 최적화를 제한시켜, 해당 변수에 대한 쓰기&읽기 작업은 즉각 메인 메모리로 이루어질 것이다.<br>
> - vaolatile 키워드의 성질
>   - 가시성
>   - "happends before guarantee" 성질
> 참고) 
> - https://parkcheolu.tistory.com/16
<hr>

### 4. Lazy Initialization.Enum (열거 상수 클래스, Thread-safe)
- Enum 인스턴스의 생성은 기본적으로 Thread-safe하다. (그러므로 스레드 관련 코드가 없어져 매우 간결)<br>
  But, Enum 내에 다른 메서드가 있다면 해당 메서드가 Thread-safe한지는 따로 책임져야한다.
- 대부분의 상황에서 **원소가 하나뿐인 열거 타입**이 싱글턴을 만드는 가장 좋은 방법이다.
- But, 싱글턴이 Enum 외의 다른 클래스를 상속(extends)해야할 경우엔 사용할 수 없다. (열거 타입이 다른 인터페이스를 구현(implements)하도록 선언할 순 있다.)
```java
public enum Singleton6 {
    INSTANCE; 
}
```
- Enum방식의 장점
  - 더 간결하고, 추가 노력없이 직렬화할 수 있다.
  - 복잡한 직렬화 상황이나 리플렉션 공격에도 제2의 인스턴스가 생성되는 것을 막아준다.

> **직렬화**
> - 객체 전달 시(데이터 전송 등)에 컴퓨터가 이해할 수 있는 형식으로 바꿔주는 것을 `직렬화`라고 한다.
> - 직렬화를 해야하는 이유: "객체" 는 JAVA 에서만 의미있는 형식이므로!
> - 추가 작업없이 직렬화 및 역직렬화를 테스트해보면, 서로 다른 객체가 생성됨을 알 수 있다. 이를 방지하고자 `readResolve` 메서드를 정의하여 같은 instance를 return하여 인스턴스 생성을 막는다.<br>
> (why? 역직렬화하면서 새로운 인스턴스를 생성하면 이는 싱글턴 패턴에 어긋남)
```java
import java.io.Serializable;

public final class MySingleton implements Serializable {
	private static final MySingleton INSTANCE = new MySingleton();

	private MySingleton() {
	}

	public static MySingleton getINSTANCE() {
		return INSTANCE;
	}

    // readResolve 메서드를 정의한다.
	private Object readResolve() {
        // 싱글턴을 보장하기 위함!
		return INSTANCE;
	}
}
```
> - 싱글톤 클래스에 `readResolve` 메서드를 직접 정의하여, 역직렬화 과정에서 만들어지는 인스턴스 대신에 기존에 생성된 싱글톤 인스턴스를 반환하도록 한다.
> - 역직렬화 과정에서 자동으로 호출되는 `readOject` 메서드가 있지만 `readResolve` 메서드에서 반환되는 인스턴스로 대체된다. (`readObject` 메서드로 만들어진 인스턴스는 가비지 컬렉션 대상이 된다.)
> - 직렬화 예시
> ![image](https://user-images.githubusercontent.com/66311276/138712486-7f7b99e2-dbd8-4ffe-a69c-2340f117ffd4.png)

<br><hr>

### 5. Lazy Initalization.LazyHolder (게으른 홀더, Thread-safe)
- 가장 많이 사용되는 싱글턴 구현 방식
> 이 방법을 왜 가장 많이 사용하는가?
> 1. synchronized 키워드를 사용하지않고도 동시성 문제를 해결하기 때문에 성능이 뛰어나다.
> 2. 인스턴스를 생성하는 내부클래스를 `static`으로 선언함으로써, `getInstance()` 에서 내부클래스를 호출할 때 한번 로드(동적 바인딩)되고 **수정 불가능**하다. <br>
> 결론은 위의 장점들을 조합했기 때문.

```java
public class Singleton7 {

    private Singleton7() {
      System.out.println("생성자 호출!!");
    }
    /**
     * static member class
     * static 멤버, 특히 static 메서드에서 사용될 목적으로 선언
     */

    private static class InnerInstanceClazz {
        // static: 클래스 로딩 시점에서 생성 -> 수정 불가능
        private static final Singleton7 uniqueInstance = new Singleton7();
    }

    public static Singleton7 getInstance() {
	        return InnerInstanceClazz.uniqueInstance;  
					// 내부클래스를 호출(이 시점에 생성)해서 반환
    }
}
```

<br>

> 참고 link
> - https://webdevtechblog.com/%EC%8B%B1%EA%B8%80%ED%84%B4-%ED%8C%A8%ED%84%B4-singleton-pattern-db75ed29c36
> - https://blog.riyenas.dev/59
