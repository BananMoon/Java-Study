package Ch4_클래스와_인터페이스.Item17;
/* 불변 복소수 클래스 Complex */
public final class Complex {
    public final double re;
    public final double im;

    // 불변 클래스라면 한번 만든 인스턴스를 재활용하기를 권한다.
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    public Complex (double re, double im) {
        this.re = re;
        this.im = im;
    }
    // 실수부와 허수부 값 반환하는 접근자 메서드
    public double realPart() {  return re;  }
    public double imaginaryPart() {  return im;  }

    // 사칙연산 메서드 : 인스턴스 자신을 수정x, 새 Complex 인스턴스를 만들어 반환
    //      함수형 프로그래밍 (피연산자에 함수를 적용하지만 피연산자 자체는 그대로인 프로그래밍 패턴)
    //       -> 불변 영역의 비율이 높아진다.
    // 전치사 명명 : 해당 method가 객체의 값을 변경하지 않는단 사실을 강조.
    public Complex plus (Complex c) {
        return new Complex(c.re + re, c.im + im);

    }
    public Complex minus (Complex c) {
        return new Complex(c.re - re, c.im - im);
    }
    public Complex times (Complex c) {
        return new Complex(c.re * re - c.im * im,
                                                            c.im * im + c.re * re);
    }
    public Complex devidedBy (Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                                                            (re * c.re + im * c.im) / tmp);
    }

}

