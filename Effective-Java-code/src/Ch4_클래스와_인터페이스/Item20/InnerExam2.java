package Ch4_클래스와_인터페이스.Item20;

public class InnerExam2 {
    // 2. 정적 클래스
    static class Cal {
        int value = 0;
        public void plus(int i, int j) {
            value = i+j;
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        // 외부 클래스로 직접 접근할 수 있다.
        // 내부 클래스가 정적 클래스이므로 인스턴스 생성 없이 접근 가능하다.
        InnerExam2.Cal cal = new InnerExam2.Cal();
        cal.plus(2,3);
        System.out.println(cal.value);
    }
}
