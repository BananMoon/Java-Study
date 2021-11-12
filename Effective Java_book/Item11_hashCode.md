## hashCode 메서드
- 객체 내부의 필드값을 hash값으로 바꿔 hash값을 리턴해준다.
- 같은 값은 무조건 동일 hash 값을 가진다.
- But, hash값이 동일하더라도 값이 다를 수 있으므로 equals 메서드와 함께 확인해야한다.
- equals 메서드보다 hash값 도출 연산이 더 빠르기 때문에 **값이 다른 객체는 대부분 hashCode에서 걸러낼 수 있다.**
<br>

### Object 클래스의 메서드
- hashCode 메서드 또한 equals와 마찬가지로 가장 기본적인 Object 클래스에서 갖고 있는 메서드 중 하나이다.
- 기본 Object의 hashCode 메서드에서는 객체가 다르면 다른 hash값을 반환하므로 오버라이딩해줘야 한다.

```java
public class Main {
  public static void main(String[] args) {
    Sub s1 = new Sub();
    s1.a = "abc";
    
    Sub s2 = new Sub();
    s2.a = "abc";
    
    // 문자열은 값으로 비교하도록 오버라이딩되었기 때문에 같은 해시값
    System.out.println(s1.a.hashCode());
    System.out.println(s2.a.hashCode());
    
    // 객체에 대한 해시값은 다르므로 다른 해시값
    System.out.println(s1.hashCode());
    System.out.println(s2.hashCode());
  }
}
```

## hashCode 메서드의 오버라이딩
Sub 클래스에서 hashCode메서드를 오버라이딩해보자.<br>

```java
class Sub {
  int x;
  String a;
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (( a == null ) ? 0 : a.hashCode()); // 문자열이 있으면 문자열의 해시코드를 더해준다.
    
    result = prime * result + x;  // 다시 한번 더 prime 값을 써서 값을 변경하고 정수값을 더해준다.
    return result;
  }
}
```


### 추가 설명 예정
