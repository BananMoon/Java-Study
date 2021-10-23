# Item3. private 생성자나 열거 타입으로 싱글턴임을 보증하라.

## 1. 싱글턴 (Singleton)
- 인스턴스가 오직 1개만 생성되어야하는 경우 사용되는 패턴 (디자인 패턴 중 하나)
- 하나의 인스턴스를 메모리에 등록 -> 여러 스레드가 동시에 해당 인스턴스를 공유하여 사용할 수 있음 -> 요청이 많은 곳에서 효율성↑
- 주의해야할 점 : **동시성(Concurrency) 문제** 고려해서 싱글턴 설계해야 함<br>
  (➡️[동시성 문제 설명](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Item3_%EB%8F%99%EC%8B%9C%EC%84%B1%EB%AC%B8%EC%A0%9C.md) 참고)

<br>

## 싱글턴 패턴 구현에 사용되는 몇가지 이디엄(오랫동안 널리 사용되어 온) 방식
### 1. Eager Initialization (이른 초기화, **Thread-safe**)
- ❓ `static` 키워드의 특징을 이용
- ❓ 클래스 로더가 초기화하는 시점에 (컴파일 시점에 성격이 결정되는)정적 바인딩을 통해 인스턴스를 메모리에 등록해서 사용하는 것
- 클래스 로더(라이브러리를 위치시키고 내용물을 읽고 라이브러리들 안에 포함된 클래스들을 읽는 역할. 자바 클래스를 로드하는 역할)에 의해 클래스가 최초로 로딩될 때 객체가 생성되기 때문에 **Thread-safe**하다.
- 싱글턴 구현 시 중요한 점! 멀티 스레딩 환경에서도 동작 가능하게 구현, 즉 Thread-safe가 보장되어야 한다.<br>

<예시> 아래 Singleton 클래스는 로드될 때 한개의 인스턴스 uniqueInstance가 생성되는 것을 알 수 있다.
```java
public class Singleton {
    // Eager Initialization
    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
      return uniqueInstance; 
    } 
}
```
<hr>

### 2. Lazy Initialization with synchronized (동기화 블럭, Thread-safe) : `synchronized` 키워드를 이용한 게으른 초기화 방식
- ❓ 게으른 초기화 방식이란, 컴파일 시점에 인스턴스를 생성 ✖️, **인스턴스가 필요한 시점에 요청**하여 동적 바인딩(런타임 시에 성격 결정)을 통해 인스턴스를 생성하는 방식이다.
- 메서드에 동기화 키워드를 부여함으로써 Thread-safe를 보장한다.
```java
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

    // Lazy Initailization
    public static synchronzied Singleton getInstance() {
      if(uniqueInstance == null) {
         uniqueInstance = new Singleton();
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
public class Singleton {
    private volatile static Singleton uniqueInstance;

    private Sigleton() {}

    // Lazy Initialization. DCL
    public Singleton getInstance() {
      if(uniqueInstance == null) {
         synchronized(Singleton.class) {    // uniqueInstance가 아직 생성되지 않은 경우에만, 동시성 블록을 부여해서 인스턴스 생성!
            if(uniqueInstance == null) {
               uniqueInstance = new Singleton(); 
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
public enum Singleton {
    INSTANCE; 
}
```
- Enum방식의 장점
  - 더 간결하고, 추가 노력없이 직렬화할 수 있다.
  - 복잡한 직렬화 상황이나 리플렉션 공격에도 제2의 인스턴스가 생성되는 것을 막아준다.

### 5. Lazy Initalization.LazyHolder (게으른 홀더, Thread-safe)


> - https://webdevtechblog.com/%EC%8B%B1%EA%B8%80%ED%84%B4-%ED%8C%A8%ED%84%B4-singleton-pattern-db75ed29c36
