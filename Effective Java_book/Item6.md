## Item6. 불필요한 객체 생성을 피하라.

### 1. 객체를 재사용하라
- 불변 객체는 언제든 재사용할 수 있다. (새로운 인스턴스를 매번 만드는 대신 하나의 인스턴스를 사용한다.)
```java
        String s = new String("hello"); // Heap 영역에 존재
        String a = "hello";   //String constant pool 영역에 존재
        String b = "hello";
        System.out.println(s==a);   //false
        System.out.println(a==b);   //true
``` 
- 같은 JVM에서 문자열 리터럴(`"`큰따옴표로 둘러싼 문자의 연속체`"`)을 사용하는 모든 코드가 같은 객체를 재 사용함이 보장된다.
- String constant pool 영역에 있는지 검색한 후, String 객체를 재사용한다.
<hr>

### 2. 불변클래스 - 정적 팩터리 메서드를 사용해 불필요한 객체 생성 회피
> [참고 글](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Item6_%EB%B6%88%EB%B3%80(Immutable).md)
<hr>

### 3. 생성 비용이 비싼 객체라면 캐싱하여 재사용하라
- 예) 정규표현식으로 문자열 형태를 확인하는 경우 : 정규표현식용 `Pattern` 인스턴스는 입력받은 정규표현식에 해당하는 유한 상태 머신(finite state machiine)을 만들어 인스턴스 생성 비용이 높다. <br>
➡️ 한번 쓰고 버려져서 GC 대상이 되는데, 이를 클래스 초기화(정적 초기화) 과정에서 직접 생성해 캐싱해두고, 나중에 `isRomanNumeral` 메서드가 호출될 때마다 이 인스턴스를 재사용한다.

> *유한장치란,* <br>
> 주어지는 모든 시간에서 처해 있을 수 있는 유한개의 상태를 가지고 주어지는 입력에 따라 어떤 상태에서 다른 상태로 전환하거나 출력이나 액션이 일어나게 하는 장치

```java
// 아래 String.matches메서드 내부에서 생성하는 Pattern 인스턴스가 문제!
static boolean isRomanNumeralSlow(String s) {
  return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                   + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}

// 값비싼 객체를 한번 생성&캐싱하여 재사용해 성능을 개선한다.
private static final Pattern ROMAN = Pattern.compile( // 존재 자체도 모를 수 있는 Pattern 인스턴스를 static final로 끌어냄.
  "^(?=.)M*(C[MD]|D?C{0,3})"
  + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

static boolean isRomanNumeralFast(String s) {
  return ROMAN.matcher(s).matches();  // isRomanNumeralFast 메서드 호출될 때마다 재사용
}
```
<br>
- But, 개선된 `isRomanNumerals` 방식의 클래스가 초기화된 후 호출되지 않는다면 `ROMAN` 필드는 쓸데없이 초기화된 꼴이다. <br>
➡️ 이때 **지연 초기화(lazy initialization)**를 쓸수 있지만 권하지 않는다. 성능이 크게 개선되지 않는 경우가 많기 때문(지연 초기화하는 필드에 접근하는 비용이 커짐.)
<hr>

### 4. 어댑터를 사용하라
- 어댑터 : 실제 작업은 뒷단 객체에 위임하고, 자신은 제2의 인터페이스 역할을 해주는 객체
> [어댑터에 대한 참고 링크](https://github.com/BananMoon/Java-Study/blob/main/Effective%20Java_book/Item6_%EC%96%B4%EB%8C%91%ED%84%B0(Adapter).md)
- Map 인터페이스의 `KeySet` 메서드(Map 객체 내의 **키**를 전부 담은 Set 뷰를 반환하는 메서드) 는 뷰 객체를 여러개 만드는게 아닌, 매번 같은 인스턴스를 반환한다.
```java
@DisplayName("keyset은 같은 Map을 바라본다")
@Test
void keyset(){
  Map<String, Object> m = new HashMap<>();
  m.put("name", "moonz");

  Set<String> mSet1 = m.keySet();
  Set<String> mSet2 = m.keySet();

  assertThat(mSet1).isSameAs(mSet2);  // 통과
}
```
<hr>

### 5. 불필요한 객체를 만들어내는, 오토박싱 (auto boxing)
- 기본 타입과 래퍼 클래스를 섞어 썼을 때 자동(auto)으로 변환해주는 역할 (ex. long -> Long)
```java
private static long sum(){
  Long sum = 0L;	
  for(long i =0; i <= Integer.MAX_VALUE; i++){
    sum += i;	// 불필요한 Long 인스턴스가 만들어진다.
  }
  return sum;
}
```
- 박싱된 기본 타입보다는 기본타입을 사용하고, 의도치 않은 오토박싱이 발생하지 않도록 주의해야 한다. 
<hr>

### 6. 아주 무겁지 않은 이상, 객체 풀(pool)은 만들지 말자.
- 데이터베이스 연결 같이 생성 비용이 비싼 경우는 재사용하는 편이 낫다.
- But, 일반적으로 자체 객체 풀은 코드를 헷갈리게 하고 메모리 사용량 ↑, 성능 ↓

> *객체 풀(Object pool)이란,*<br>
> - 객체를 매번 할당, 해제하지 않고 고정 크기 풀에 들어있는 객체를 재사용함으로써 메모리 사용 성능을 개선함.
> - 객체들의 크기가 비슷 or 객체를 빈번하게 생성/삭제하는 경우 or 생성 비용이 비싼 객체를 캡슐화하는 경우 사용함. 
> - 객체를 위한 메모리 크기가 고정되어 가장 큰 자료형에 맞춰야하고, 메모리 낭비 가능성이 있어 사용에 주의해야 함.
<br>

### 마지막으로, 방어적 복사가 필요한(수정&삭제가 금지되어야하는) 상황에서 객체를 재사용했을 때의 피해가 훨씬 크다. (필요없는 객체를 반복생성한 때보다)
- 방어적 복사 실패시, 언제 터질지 모르는 버그와 보안 구멍...
