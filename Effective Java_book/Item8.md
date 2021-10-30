## 📌Finalizer
- 예측 불가능, 상황에 따라 위험, 일반적으로 불필요
- 오동작, 낮은 성능, 이식성 문제의 원인
- Finalizer는 GC(Garbage Collection)가 발생한 후에 호출되는 메서드인데, 모든 변수가 불필요해졌다해서 GC가 바로 발생하는 게 아니다. 심지어 호출이 안될 수도 있다.
<br>

**[실행 클래스]**
```java
public class SampleRunner {
    public static void main(String[] args) throws InterruptedException {
        SampleRunner runner = new SampleRunner();
        runner.run();
        Thread.sleep(1000l);
        // System.gc();   finalize를 호출할 수도 안할 수도!
    }

    private void run() {
        FinalizerExample finalizerExample = new FinalizerExample();
        finalizerExample.hello();
    }

}
```
<br>

**[finalizer 클래스]**
```java
public class FinalizerExample {
  @Override
  protected void finalize() throws Throwable {
    System.out.println("Clean up");
  }
  
  public void hello() {
    System.out.println("hi");
  }
}
```
- `finalizerExample` 변수가 GC의 대상이 되는건 맞지만, 무조건 처리되지는 않는다.

<br>

➡️ 자바 9에서는 `finalizer`를 사용자제 API로 지정하고, 그 대안으로 `cleaner`를 소개했다. 이는 `finalizer`보다 덜 위험하지만 여전히 예측 불가, 느림, 일반적으로 불필요

> Java에서는 !
> - 접근할 수 없게된 객체를 회수하는 역할 : 가비지 컬렉터 (Garbage Collector)
> - 비메모리 자원을 회수하는 용도 : `try-with-resources`와 `try-finally` 사용
<br>

## 📌finalizer와 cleaner의 단점
### 1. 즉시 수행된다는 보장이 없다.
- 이들이 실행되기까지 얼마나 걸릴지 알 수 없다. (제때 실행되지 않을 수도)
- **제때 실행되어야하는 작업을 finalizer나 cleaner에서 하면 안된다.**
  - 예) 파일 리소스를 반납하는 작업(파일 닫기)을 맡기면, 실제로 finalizer&cleaner 실행을 게을리하여 파일을 닫지 못하면 새 파일을 열지 못해 프로그램이 실패하는 결과를 초래한다.
- 가비지 컬렉터가 finalizer와 cleaner를 수행시키기 때문에 얼마나 신속히 수행될지는 알 수 없다.
<br>

### 2. 인스턴스 반납 (자원 회수)이 제멋대로 지연될 수 있다.
- finalizer 스레드는 다른 애플리케이션 스레드보다 우선 순위가 낮다. ➡️ 실행될 기회를 얻지 못했다. ➡️ 인스턴스의 자원 회수가 되지 않는다. ➡️ OutOfMemoryError 발생하며 애플리케이션 죽다.
<br>
👉 자바 언어 명세에서는 어떤 스레드가 finalizer를 수행할지 명시하지 않으므로 해결 방법은 **finalizer를 사용하지 않는 것**.
- cleaner는 자신을 수행할 스레드를 제어할 수 있다는 면에서 조금 낫다. But, 여전히 백그라운드 수행 & 가비지 컬렉터의 통제하  ➡️ 즉각 수행 보장 X
<br>

### 3. 수행 여부조차 보장되지 않는다.
- 접근 불가한 일부 객체에 딸린 종료 작업을 전혀 수행 못한 채 프로그램이 중단될 수도 있다.
- **상태를 영구적으로 수정하는 작업에서는 절대 finalizer&cleaner에 의존해서는 안된다.**  ex) DB같은 공유자원의 영구 lock 해제 -> 분산 시스템 전체가 서서히 멈출 것임...
- `System.gc`나 `System.runFinalization` 메서드에 현혹되지 말자. 위 둘을 실행될 가능성을 높여줄 순 있으나 보장해주진 않는다.
  - 이를 보장해주겠다는 `System.runFinalizersOnExit`와 `Runtime.runFinalizersOnExit` 또한 심각한 결함으로 사용제한되었다.
<br>

### 4. finalizer 동작 중 발생하는 예외는 무시되며, 그 순간 종료된다.
- 예외를 잡지 못해 해당 객체가 마무리가 덜 된 상태로 남을 수 있다. 이를 다른 스레드가 사용하게 될 수도..
- 보통의 경우, 잡지못한 예외는 스레드를 중단시키고 스택 추적 내역을 출력하지만, **finalizer에서 일어날 경우 경고조차 출력하지 않는다.**<br>
👉 그나마 `cleaner`를 사용하는 라이브러리는 자신의 스레드를 통제하기 때문에 발생하지 않는 문제이다.
<br>

### 5. 심각한 성능 문제를 동반한다.
- `AutoCloseable` 객체 생성 후 가비지 컬렉터가 수거하기 까지 
  - `try-with-resources`로 닫으면 12ns 소요
  - `finalizer`를 사용하면 550ns가 소요    ➡️ finalizer를 사용한 객체 생성&파괴하면 50배 느려짐
  - `cleaner`도 finalizer와 비슷함<br>
👉 But, 이들을 안전망 형태로만 사용하면, 매우 빨라진다. (객체 생성, 정리, 파괴까지 약 66ns 소요) -> 안전망을 설치하는 대가로 성능이 12ns보다 약 5배정도 느려진다는 뜻
<br>

### 6. finalizer 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬 수 있다.
1. 클래스A를 공격하려는 클래스B가 클래스A를 상속받는다.
2. 상속받은 클래스B(나쁜놈)는 클래스A의 finalizer를 오버라이딩해놓았다.
3. 클래스B가 인스턴스를 생성할 때 객체 생성을 막기 위해 예외를 던지지만, <br>
   이때 finalizer가 실행이되도록 해놓아서 인스턴스가 죽지않고, 이 죽지않은 인스턴스의 메서드를 호출해 노출되지 않아야할 작업을 접근&수행할 수 있게된다.

> 👉 해결방법 : <br>
> 상속 자체를 막는 `final` 키워드를 클래스A에 추가 -> 하위 클래스 생성 불가
>   - 확장해야하는 클래스라면, `finalize` 메서드에 final키워드를 추가 -> 해당 메서드를 오버라이드 불가
<br>

## 📌종료해야할 자원(파일/스레드 등)을 담고있는 객체의 클래스에서의 대안책
1. `AutoCloseable`을 구현(implements)
  - 인터페이스`AutoCloseable`는 추상 메서드 `close`를 갖고있다.<br>
2. 클라이언트에서 인스턴스를 다 쓴 후 `close` 메서드를 호출  (예외 발생해도 제대로 종료되도록 `try-with-resources` 사용!!)
<br>

**[자원 담고있는 객체의 클래스]**
```java
public class SampleResource implements AutoCloseable {  // 1. AutoCloseable 구현(implements)

    @Override
    public void close() throws Exception {  // 2. "클라이언트"에서 인스턴스를 다 쓰고나면 close 메서드 호출
        System.out.println("Clean up");
    }
    public void hello() {
        System.out.println("hi");
    }
}
```
<br>

**[클라이언트 클래스]이자 권장하는 방법 - `try-with-resources**
```java
public class SampleRunner {
    public static void main(String[] args) throws Exception {
        /* 자바 7부터는 try-with-resources를 사용하여 암묵적으로 close 호출 */
        try( SampleResource sampleResource = new SampleResource()) {
            sampleResource.hello();
        }   //따로 close()를 호출하지 않아도 try 블록이 끝날때 AutoCloseable 인터페이스에 있는 close()를 호출
        
        /* 기존에는 close() 메서드 호출을 보장하기위해 try-finally 사용 
        SampleResource sampleResource = null;
        try {
            sampleResource = new SampleResource();
            sampleResource.hello();
        } finally {
            if (sampleResource != null) {   //값이 비지 않았으면
                sampleResource.close(); // 2. "클라이언트"에서 인스턴스를 다 쓰고나면 close 메서드 호출
            }
        }
        */
    }
}
```
<br>

## 📌그럼 finalizer, cleaner는 언제 적절히 쓸까?
> 1. 안전망 역할로서 자원을 반납하고자 하는 경우
> 2. 네이티브 리소스를 정리해야하는 경우

#### 1. "클라이언트에서 객체를 close하지 않았을 때"를 대비한 안전망 역할로 쓰인다.
- 자원 반납에 사용될 `close` 메서드를 client가 호출하지 않은 경우, (언제 호출될지 모르지만 안하는 것보단 나으므로) 오버라이드한다.
- 자바 라이브러리의 일부 클래스(FileInputStrea, FileOutputStream, ThreadPoolExecutor)는 **안전망 역할의 finalizer**를 제공한다.
<br>

**[SampleResource 클래스에 추가]**
```java
public class SampleResource implements AutoCloseable {  // 1. AutoCloseable 구현(implements)
    // 기존
    @Override
    public void close() throws Exception {  // 2. "클라이언트"에서 인스턴스를 다 쓰고나면 close 메서드 호출
        System.out.println("Clean up");
    }
    public void hello() {
        System.out.println("hi");
    }

    // 1. finalize로 안전망 역할. client가 close()를 호출하지 않은 경우를 대비
    @Override
    protected void finalize() throws Throwable {
        if (!this.closed) { // 객체가 안 닫혔으면(false면) close() 호출
            close();
        }
    }
}
```
<br>

#### 2. 네이티브 피어 (native peer)와 연결된 객체에서 사용
> 네이티브 피어란?<br>
> 일반 자바 객체가 네이티브 메서드를 통해서 기능을 위임한 객체

- GC는 네이티브 객체의 존재를 모른다. (자바 객체가 아니니)
- 네이티브 피어가 가지고있는 자원이 중요하지 않은(non-critical) 자원일 때에만, `finalizer`나 `clear를 사용해서 해당 자원을 반납할 수 있다.
- But, 중요한 리소스라면 (성능 저하를 감당할 수 없거나 즉시 자원을 회수해야 한다면) `close` 메소드를 사용하자.
<br>

## 📌cleaner를 안전망으로 사용하기
> cleaner를 사용하려면 JDK 버전을 9 이상으로 설정해주어야 한다. (JDK 9부터 finalize는 deprecate되었다.)
- 가장 Best는 close()를 호출하는 것인데, 클라이언트가 이를 호출하지 않은 경우, 안전망인 `cleaner`를 사용해야 한다. (단지 안전망으로만 쓰이는 Room의 cleaner)
- 만약 클라이언트가 모든 Room 생성을 `try-with-resources` 블록으로 감쌀 경우, 자동 청소는 전혀 필요 없다. (위 코드 참고)

**[cleaner를 안전망으로 사용하는 클래스 코드]**
```java
import java.lang.ref.Cleaner;
public class Room implements AutoCloseable {

    // clean 작업을 할 별도의 스레드 생성
    // 해당 클래스 내에서는 Room(바깥 객체)의 자원을 참조하면 안되므로 "static" 키워드!
    // -> 순환참조가 생겨 GC가 Room 인스턴스를 회수할 기회가 오지 않아 정리 작업이 이루어지지 않는다.
    private static class RoomCleaner implements Runnable {
    
        // run 메서드 내에서 정리 작업이 이루어져야 함.
        // 1. Room의 close()메소드 호출 (-clean() 호출)  2. GC가 Room 회수할 때까지 client가 close호출 안할 시, cleaner가 호출
        @Override
        public void run() {
            System.out.println("방 청소");
            numTrash = 0;   // 자원 회수
        }

        int numTrash;   // 방(room) 안 쓰레기 수
        RoomCleaner(int numTrash) {
            this.numTrash = numTrash;
        }
    }

    // static 메서드인 create()로 (안전망으로 사용할) CLEANER 객체 생성
    private static final Cleaner CLEANER = Cleaner.create();
    
    // cleanable과 공유할 방의 상태 인스턴스
    private final RoomCleaner roomCleaner;

    // CLEANER를 직접 쓰는게 아닌 Cleanable 인터페이스로 인스턴스 선언
    private final Cleaner.Cleanable cleanable;

    // 생성자
    public Room(int numTrash) {
        roomCleaner = new RoomCleaner(numTrash);
        cleanable= CLEANER.register(this, roomCleaner);  // CLEANER 객체로 Room과 roomCleaner를 등록해서 얻음
    }

    @Override
    public void close() throws Exception {
        // AutoCloseable 인터페이스의 추상메서드 clean() 호출 -> 정리 작업하는 RoomCleaner 클래스의 run() 호출
        cleanable.clean();
    }
}
// 1. 클라이언트가 자원을 사용한 후 Room의 close() 호출 -> cleanable의 clean() 호출 -> roomCleaner의 run() 호출 -> 회수
// 2. GC가 Room 회수할 때까지 클라이언트가 close() 호출 X -> 안전망!!! cleaner가 roomCleaner의 run() 호출 -> 회수
```
<hr><br>

### 👉 결론: cleaner (~JAVA 8: finalizer)는 1. 안전망 역할, 2. non-critical한 네이티브 자원 회수용 으로만 사용하자. (사용 시에도 불확실성과 성능 저하에 주의하자.) 
