package Ch4_클래스와_인터페이스.Item20;
// 메서드 안에서 선언되는 지역 중첩 클래스
public class InnerExam3 {
    public void exec() {
        class Cal {
            int value = 0;
            public void plus(int i, int j) {
                value = i+j;
                System.out.println(value);
            }
        }
        // 메서드 exec 안에서만 해당 클래스에 접근할 수 있다.
        Cal cal = new Cal();
        cal.plus(2,3);
        System.out.println(cal.value);
    }

    public static void main(String[] args) {
        InnerExam3 t = new InnerExam3();
        t.exec();   // 내부적으로 Cal 인스턴스가 생성이되어 메서드가 실행됨.
    }
}
