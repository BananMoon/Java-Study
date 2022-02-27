> 클래스들은 가변적이여야 하는 매우 타당한 이유가 있지 않는 한 반드시 불변으로 만들어야 한다. 만약 클래스를 불변으로 만드는 것이 불가능하다면, 가능한 변경 가능성을 최소화하라.

### 불변 객체
객체 생성 이후 내부의 상태가 변하지 않는 객체<br>
read-only만을 제공(제공하는 경우, 방어적 복사를 통해 제공)  * *방어적 복사 : 참조(reference)를 통해 값을 수정하면 내부의 상태가 바뀌므로 내부를 복사하여 전달하는 것*<br>
ex) String<br>
```java
String name = "Old";
name="New";	 // 내부의 char를 변경하는 것이 아닌 새로운 값을 지니는 객체를 생성하는 것
```
<hr>

### 👉 불변(Immutable) 객체 및 final을 사용해야하는 이유
#### 1. Thread-safe하여 병렬 프로그래밍에 유용 & 동기화를 고려하지 않아도 된다.
- 멀티 쓰레드 환경에서 발생하는 동기화문제가 발생하지 않기 때문에, 안전성 보장 뿐아니라 동기화하지않음으로써 성능상의 이점도 있다.
<br>

#### 2. 실패 원자적(Failure Atomic)인 메소드를 만들 수 있다.
- 가변 객체를 통해 어떤 작업을 하는 도중 예외가 발생하면 불안정한 상태에 빠지고, 그러한 객체는 또 다른 에러를 유발할 수 있다.
- But, 불변 객체는 어떤 예외가 발생해도 메소드 호출 전의 상태를 유지할 수 있고, 예외가 발생해도 발생하지 않은 것처럼 다음 로직을 처리할 수 있다.
<br>

#### 3. Cache나 Map 또는 Set 등의 요소로 활용하기 더욱 적합하다.
- Cache, Map, Set 등으로 사용되는 객체가 변경되면 이를 갱신하는 등의 작업을 추가로 해주어야 할 것이다.
- But, 객체가 불변이라면 한번 데이터가 저장된 이후 다른 부가 작업들을 고려하지 않아도 될 것이고, 이는 캐시나 다른 구조를 사용하는데 용이하게 작용한다.
<br>

#### 4. 부수효과(Side Effect) 를 피해 오류 가능성을 최소화할 수 있다.  
- *부수 효과란 변수의 값이 변경되거나 필드 값이 설정되는 등의 변화가 발생하는 효과*
- 만약 객체의 수정자(Setter)가 구현돼있고, 여러 메소드에서 객체의 값이 변경된다면 객체를 예측하기 어려워진다.
- 객체의 바뀐 상태를 파악하기 위해 메서드를 살펴봐야할 것이며 이런 부분은 유지보수성을 상당히 떨어뜨린다.
- 이러한 부수효과가 없는 순수 함수들을 만드는 것이 중요한데, 객체가 불변이라면, 변경 가능성이 적으며 객체의 생성과 사용이 상당히 제한 ➡️ 자연스럽게 순수함수들로 구성 & 다른 메소드가 호출되어도 객체의 상태가 유지되므로 안전하게 다시 사용 가능<br>
➡️ **불변객체는 오류를 줄여 유지보수성이 높은 코드를 작성하도록 도와준다.**
<br>

#### 5. 다른 사람이 작성한 함수를 예측가능하며 안전하게 사용할 수 있다.
- 불변성은 협업 과정에서도 도움을 준다. 다른 사람이 개발한 함수가 불변성이 보장된 함수라면 위험없이 이용가능하다.
- 또한 다른 사람이 내가 작성한 메서드를 호출해도 값이 변하지 않음을 보장받을 수 있다.
<br>

#### 6. 가비지 컬렉션의 성능을 높일 수 있다.
- 가변 객체를 전달할 때는 복제본을 생성(새 객체 할당)해야 하지만, <br>
  불변 객체의 경우, 변경될 가능성이 없기 때문에 원본을 그대로 반환할 수 있기 때문에 GC에 부담이 되지 않는다.
```java
 Date getDate(){
   return copy(this.date);
 }
 LocaDate getDate() { //java-8 dates는 불변!
   return this.date;
}
```

> [참고 링크](https://stackoverflow.com/questions/35384393/how-do-immutable-objects-help-decrease-overhead-due-to-garbage-collection/35384460#35384460)<br>
> [가비지 컬렉션 동작원리](https://mangkyu.tistory.com/118)
<hr>

```java
public class MutableHolder { 
  private Object value; 
  public Object getValue() { return value; } 
  public void setValue(Object o) { value = o; }
} 
public class ImmutableHolder { 
  private final Object value; 
  public ImmutableHolder(Object o) { value = o; } 
  public Object getValue() { return value; } 
  
  public static void main(String[] args) {
        ImmutableHolder ins = new ImmutableHolder("s");
        ins = "s";    //incompatible types: int cannot be converted to ImmutableHolder
        System.out.println(ins.getValue());
        System.out.println(2);
    }
}
```

➡️ 결국 불변 객체 활용 시, 가비지 컬렉터가 스캔해야되는 객체 수가 줄어 스캔할 메모리 영역과 빈도수가 줄어든다. 그럼 GC가 수행되어도 지연 시간을 줄일 수 있다.<br>
   (그러므로 필드값을 수정할 수 있는 MutableHolder보다는 필드값을 수정할 수 없는 ImmutableHolder를 사용하는 것이 좋다.
<hr>

### java에서 불변 객체 생성 방법
**final 키워드**
- 기본적으로 가변적인 Java 변수들에 `final` 키워드를 붙여 참조값을 변경 못하도록 한다. -> 불변성 확보
```java
final String name="old";
name= "new";  // cannot assign a value to final variable name
```
- 그럼에도 List의 경우, 새로운 객체가 더해져도 문제되지 않는다. 

**불변 클래스**
- `final`로 선언한다.
- 모든 클래스 변수를 `private`과 `final`로 선언한다.
- 객체를 생성하기 위한 생성자 또는 **정적 팩토리 메소드**를 추가한다.
- 참조에 의해 변경 가능성이 있는 경우 **방어적 복사**를 이용해서 전달하라.

```java
public final class ImmutableClass {
  private final int age;
  private final String name;
  private final List<String> people;
  
  private ImmutableClass(int age, String name) {  // private 생성자
    this.age = age;
    this.name = name;
    this.people = new ArrayList<>();
  }
  
  public static ImmutableClass of (int age, String name) {  // 정적 팩터리 메소드를 통해 객체 생성
    return new Immutableclass(age, name);
  }
  
  public int getAge() { return age; }
  public String getName() { return name; }
  public List<String> getPeople() { 
    return Collections.unmodifiableList(people);  // 방어적 복사를 통해 값 반환: 리스트에 데이터 추가, 삭제행위가 금지되는 리스트 반환 메서드
  }
}
```
