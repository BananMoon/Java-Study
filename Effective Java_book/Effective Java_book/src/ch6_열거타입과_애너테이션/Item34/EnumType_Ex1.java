package ch6_열거타입과_애너테이션.Item34;

public class EnumType_Ex1 {
    // 지양1 : 정수 열거 패턴
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;
    // 단점
    // 1. 타입 안전을 보장할 방법이 없고, 표현력이 좋지 않다.
    // 2.  정수 열거 패턴을 위한 별도의 이름공간(namespace)을 지원하지 않아 접두어를 써서 이름 충돌을 방지한다.
    // 3. 프로그램이 깨지기 쉽다.
    // 4. 정수 상수는 문자열로 출력하기 다소 까다롭다.(단지 숫자로만 보인다.)

    // 지양2 : 문자열 상수를 사용하는 변형 패턴
    // 상수의 의미를 출력할 수 있다는 점만 장점
    // 단점 1. 문자열 값을 그대로 하드코딩해야 한다. (오타를 컴파일러는 확인할 수 x, 런타임 버그 발생)
    // 단점 2. 문자열 비교에 따른 성능 저하

    /* 지향: 단순한 열거 타입
      - 열거 타입 자체가 클래스
      - 상수 하나 당 자신의 인스턴스를 하나씩 만들어 public static final 필드로 공개
      - 밖에서 접근할 수 있는 생성자를 제공하지 않으므로 사실상 final -> 클라이언트/인스턴스를 직접 생성/확장 불가. -> only 하나씩만 존재함이 보장
          즉, 인스턴스 통제된다.
      - 싱글턴 == 원소가 하나인 열거 타입
      - 열거 타입 == 싱글턴을 일반화한 형태
     */
    public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
    public enum Orange { NAVEL, TEMPLE, BLOOD }
    // 장점
    // 1. 자바의 열거 타입은 완전한 형태의 클래스이므로 훨씬 강력
    // 2. 열거 타입은 컴파일타임 타입 안전성을 제공한다. 다른 타입의 값을 넘기려하면 컴파일 오류 발생
    // 3. 각자의 이름공간이 있어 이름이 같은 상수도 평화롭게 공존한다.

}
