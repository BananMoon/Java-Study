package Ch3_모든객체의_공통메서드.Item10;
// 1) 대칭성 예제
//case-"in"sensitive : 대소문자를 구분하지 않겠다.
public final class CaseInsensitiveString {                                                          // 상속 x. Override로 인한 실수를 최소화 하고 버그를 줄이기 위해 선언
    private final String s;

    public CaseInsensitiveString(String s) {
        if (s == null) throw new NullPointerException();    // s가 null값이면 Error 발생
        this.s = s;
    }

    // equals()를 오버라이드
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseInsensitiveString) {    // 1. 오브젝트가 CaseInsensitiveString의 인스턴스이냐?
            System.out.println("CaseInsensitiveString 인스턴스!");
            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
//            CaseInsensitiveString c = (CaseInsensitiveString) obj;
//           System.out.println(s.equalsIgnoreCase(c.s));
//            return s.equalsIgnoreCase(c.s); // 대소문자를 무시하여 String 클래스의 equalsIgnoreCase() 메서드. 같으면 0 반환
        }
        if(obj instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s); // a와 b 비교 .CaseInsensitiveString 인스턴스로 s에 접근
            // a.equals(b); 일 때 a가 s, b가 obj(((CaseInsensitiveString) obj).s)
        }

        /* 문제 */
        if (obj instanceof String)   {  // 2. 오브젝트가 String의 인스턴스이냐? ->
            System.out.println("String 인스턴스!");
            return s.equalsIgnoreCase((String) obj);
        }
        return false;

      /* 문제 해결.
      해당 equals 메소드에서 String 과 상호작용 하지 않도록 변경해야 한다.
      CaseInsensitiveString클래스는 String을 알고 있다. 한 방향으로만 작동.
              // String에 대한 instanceof 부분을 빼고 구현하여 대칭성 만족!
        if(obj instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s); // a와 b 비교 .CaseInsensitiveString 인스턴스로 s에 접근
            // a.equals(b); 일 때 a가 s, b가 obj(((CaseInsensitiveString) obj).s)
        }
       */
    }
}

