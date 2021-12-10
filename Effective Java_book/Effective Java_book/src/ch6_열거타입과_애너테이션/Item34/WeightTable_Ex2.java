package ch6_열거타입과_애너테이션.Item34;

public class WeightTable_Ex2 {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble("185");   // args[0], String -> Double
        double mass = earthWeight / Planet_Ex2.EARTH.surfaceGravity();

        for (Planet_Ex2 p : Planet_Ex2.values())    // 열거 타입에 정의된 상수들의 값을 배열에 담아 반환하는 메서드
            System.out.printf("%s에서의 무게는 %f이다.\n", p, p.surfaceWeight(mass));
    }
}
