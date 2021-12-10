package ch6_열거타입과_애너테이션.Item34;
/**
 * 각 상수에 연관된 데이터와 메서드를 내재시키는 방법
 * 단순한 상수 모음이지만 클래스이다.
 */
public enum Planet_Ex2 {
    // 원래 enum 타입
    //MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTURE;

    // 각 상수와 연관된 데이터를 내재시키고 싶다?
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTURE(1.024e+26, 2.477e7);

    // 연관 데이터 선언
    private final double mass;  //질량
    private final double radius;    //반지름
    private final double surfaceGravity;    // 표면 중력

    // 중력 상수
    private static final double G = 6.67300E-11;

    // 생성자
    Planet_Ex2(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);  // 최적화를 위해
    }
    // 별도의 public 접근자
    public double mass() { return mass; }
    public double radius() { return radius; }
    public double surfaceGravity() { return surfaceGravity; }
    public double surfaceWeight(double mass) {  // 질량으로 구함
        return mass * surfaceGravity;
    }
}
