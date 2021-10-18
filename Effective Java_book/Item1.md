## 참고 사항
#### 박싱 클래스<br>
`Lisst<String> list = new ArrayList<>();`<br>
위와 같은 제네릭 타임에는 기본 자료형인 원시형(Primitive Type)은 넣지 못한다. <br>
제네릭 타입에 넣으려면 원시형을 담아내는 클래스를 넣어야한다.<br>
**즉, 원시형을 객체화한 래퍼 클래스 (Wrapper Class)를 넣어야한다.**
| 래퍼 클래스 종류 |
|:-:|
|Boolean|
|Character|
|Double|
|Float|
|Integer|
|Long|
|Short|
|Byte|

**박싱(Boxing)** : 원시형을 래퍼 클래스로 바꾸는 방법
```java
Integer x = new Inter(3); //기본형 3을 래퍼클래스로 박싱
Integer x = 3;  //Auto-Boxing
```

**언박싱(Unboxing)** : 래퍼 클래스를 원시형으로 바꾸는 방법
```java
// 형식 : {기본형} {변수} = {래퍼클래스 변수}.{기본형}Value();
Integer x = new Integer(3); //박싱
int x2 = x.intValue();  //언박싱
int x2 = x; //Auto-Unboxing
```
> 원시형과 래퍼 클래스 둘다 사용할 수 있는 경우라면, 메모리를 덜 차지하는 원시형을 사용하자





> 참고
> - https://cbw1030.tistory.com/285
