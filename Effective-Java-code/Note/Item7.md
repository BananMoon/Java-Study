# Item7. 다 쓴 객체 참조를 해제하라
- 가비지 컬렉터(다 쓴 객체를 회수하는 역할)를 갖춘 언어를 사용할 때는 메모리 관리에 더욱 신경써야 한다.

### 메모리 누수 일으키는 주범
> *메모리 누수란?*
> - 의도치않게 객체를 살려두어 객체 참조 하나를 살려둔 상태로 (그 객체+해당 객체가 참조하는 모든 객체+또 그 객체들이 참조하는 모든 객체..) 그 객체를 사용하는 프로그램을 오래 사용하게 될 경우 점차 가비지 컬렉션 활동과 메모리 사용량이 늘어나 성능이 저하된다.
> - 심할 때는 디스크 페이징이나 outOfMemoryError(더 이상 요청한 메모리를 할당할 수 없다 발생!!)를 일으켜 프로그램이 종료되기도 한다.

### 스택 (Stack)
```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
```
- 문제되는 것은 *스택에서 꺼내진 객체들*을 가비지 컬렉터가 회수하지 않는다는 것이다.
- 이 스택이 꺼내진 객체들의 **유효 기간이 지난 객체 참조(obsolete reference, 쓸데없는)**를 여전히 가지고 있기 때문.
- 해결방법은?
  - 해당 참조를 다 썼을 때 null처리 (참조 해제) 를 해주면 된다.
```java
public Object pop() {
  if (size == 0) 
    throw new EmptyStackException();
  Object result = elements[--size];
  elements[size] = null;  // 사용하려하면 NullPointerException 던지며 종료됨
  return result;
}  
```

#### But, 모든 경우에 null처리하는 것은 아니다. 오히려 지저분하게 만들 수 있다. <br>
#### 가장 좋은 방법은 **그 참조를 담은 변수를 유효 범위(scope) 밖으로 밀어내는 것** - 변수의 범위를 최소가 되게 정의하는 것.
<hr>

### 자기 메모리를 직접 관리하는 클래스와 메모리 누수 해결 방안
1. Stack
- 객체 자체가 아닌, 객체의 참조를 담는 elements 배열로 저장소 풀을 만들어 원소들을 관리한다.
- 배열의 (size보다 적은) 활성 영역에 속한 원소들이 사용되고, 비활성 영역은 쓰이지 않는다. <br>
➡️ 이를 카비지 컬렉터가 알지 못한다. GC에게는 비활성 영역에서 참조하는 객체도 똑같이 유효한 객체이다.<br>
➡️ 이를 아는 프로그래머가 비활성 영역의 객체들을 바로 null 처리 해줘야 한다.
<br>

2. Cache
- 객체 참조를 캐시에 넣고 그냥 놔두는 경우가 자주 발생한다.
- 해결방안
  1. `WeakHashMap`을 사용해 캐시를 생성 : 다 쓴 엔트리는 즉시 자동으로 제거 ➡️ 캐시 엔트리의 유효 기간이 정해진 경우에만 유용
  2. 시간이 지날수록 엔트리의 가치를 떨어트리는 방식(자주 사용) : 쓰지 않는 엔트리를 청소
    a. 백그라운드 스레드를 활용  (ex. `ScheducledThreadPoolExecutor`) 
    b.캐시에 새 엔트리를 추가할 때 부수 작업으로 수행 (`LinkedHashMap.removeEldestEntry` 메서드)
  3. (더 복잡한 캐시를 만들고 시은 경우) `java.lang.ref` 패키지를 직접 활용
<br>

3. 리스터(lister) 혹은 콜백(Callback)
- 클라이언트가 콜백 등록만 하고, 명확히 해지하지 않는 경우, 콜백은 계속 쌓여 간다.<br>
➡️ 이 때 콜백을 약한 참조 (weak reference)로 저장하면 가비지 컬렉터가 즉시 수거해간다.  ex. `WeakHashMap`에 키로 저장

> Java의 세가지 참조 방식 
1. 강한 참조 (Strong Reference)<br>
- `Integer prime = 1;` 와 같은 가장 일반적인 참조 유형
- `prime` 변수 는 값이 1 인 Integer 객체에 대한 강한 참조 를가진다.
- 이 객체를 가리키는 강한 참조가 있는 객체는 **GC대상이 되지않는다.**

2. 부드러운 참조 (Soft Reference)<br>
– `SoftReference<Integer> soft = new SoftReference<Integer>(prime);` 와 같이 `SoftReference Class`를 이용하여 생성  
- 만약 `prime` == null 상태가 되어 더이상 원본(== 최초 생성 시점에 이용 대상이 되었던 Strong Reference) 은 없고 대상을 참조하는 객체가 SoftReference만 존재할 경우, GC대상으로 들어가도록 JVM은 동작한다.   
- 다만 WeakReference 와의 차이점은 **메모리가 부족하지 않으면 굳이 GC하지 않는 점**이다. 
- 때문에 조금은 엄격하지 않은 Cache Library들에서 널리 사용되는 것으로 알려져있다.

3. 약한 참조 (Weak Reference)<br>
– `WeakReference<Integer> soft = new WeakReference<Integer>(prime);` 와 같이 `WeakReference Class`를 이용하여 생성
- `prime` == null 되면 (해당 객체를 가리키는 참조가 WeakReference 뿐일 경우) GC 대상이 된다.  
- 마찬가지로 SoftReference와 차이점은 **메모리가 부족하지 않더라도 GC 대상이 된다는 것**이다.<br>
  즉, 다음 GC가 발생하는 시점에 무조건 없어진다.
<br>  

> WeakHashMap 메서드
> - WeakReference의 특성을 이용하여 HashMap의 Element를 자동으로 제거(GC)
> - Key에 해당하는 객체가 더이상 사용되지 않는다고 판단되면 제거한다는 의미이다.
```java
public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();   // 
 
        Integer key1 = 1000;
        Integer key2 = 2000;
 
        map.put(key1, "test a");
        map.put(key2, "test b");
 
        key1 = null;
        System.gc();  //강제 Garbage Collection
 
        map.entrySet().stream().forEach(el -> System.out.println(el));  // 2000=test b (key1는 제거됨)
 
    }
}

```
➡️ 참고 링크 : http://blog.breakingthat.com/2018/08/26/java-collection-map-weakhashmap/
<hr>

> 메모리 누수는 겉으로 잘 드러나지 않아 시스템에 수년간 잠복하는 사례도 있다. 이런 누수는 철저한 코드 리뷰나 힙 프로파일러 같은 디버깅 도구를 동원해야만 발견되기도 한다. 그래서 이런 종류의 문제는 예방법을
익혀두는 것이 매우 중요하다.
