## Equals는 일반 규약을 지켜 재정의하라


다음 사진을 보면 알 수 있듯, 재정의를 하지 않게 될 경우 해당 클래스의 인스턴스는 자기자신과만 같다고 평가하게 된다. 
![image](https://user-images.githubusercontent.com/66311276/139585383-a365e43f-cdab-4b2d-b460-268ec77b31a8.png)

재정의되지 않은 Object의 equals를 사용하면 아래 코드에서 두개의 String은 같은 내용을 갖고 있지만 다른 객체로 판단한다.
이를 동등성은 성립하지만, 동일성은 성립하지 않는다는 것 -> 재정의 해야하는 경우

### 재정의하지 않아도 되는 경우
1. 각 인스턴스가 본질적으로 고유한 경우
- 

2. 인스턴스의 논리적 동치성을 검사할 일이 없는 경우
- 값을 비교해서 동등한지 비교할 일이 없다. ➡️ 논리적 동치성 검사를 할 일도 없다 ➡️ 기본적인 Object의 equals로 충분하다.

3. 상위 클래스에서 재정의한 equals가 하위 클래스에서 들어맞는 경우
- 상위에서 구현한 equals로직으로 충분한 경우 재정의 X, 상위클래스에 정의된 equals를 오버라이드
- AbstractSet은 Set 및 Set extends Collection을 구현

4. 클래스가 private이거나 package-private이고 equals를 사용할 일이 없다?
- 만약 equals가 실수로라도 호출되는 걸 막고 싶다면 다음과 같이 재정의한다.
```java
@Override
public boolean equals(Object o) {
  throw new AssertionError();
}
```

### 재정의가 필요한 경우
객체 식별성(Object identity) 이 아니라 논리적 동치성(logical equality)이 필요한 경우
➕ 상위 클래스가 있는 경우, 상위클래스에서 논리적 동치성 (logical equality) 비교하도록 재정의하지 않은 경우
ex) 값 클래스 (Integer, String)
- But, 값 클래스여도 통제 클래스(값이 여러개 만들어지지 않는) 일 경우, 재정의하지 않아도 된다.



###  재정의할 때 따라야하는 규약
1) 반사성 (reflextivity)<br>
객체는 자기 자신과 같아야 한다는 뜻
`"moon".equals("moon")` <br>
-> 이 조건은 만족하지 못하도록 하는게 더 힘들 듯함

2) 대칭성 (Symmetry)<br>
두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다는 뜻

**[규약을 위반한 코드]**

