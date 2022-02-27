package Ch4_클래스와_인터페이스.Item20;
// 내부 클래스 실습
public class InnerExam1 {
    // 1. 인터페이스 클래스 (내부 클래스)
    class Cal {
        int value = 0;
        public void plus(int i, int j) {
            value = i+j;
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
    // 내부 인스턴스 클래스 사용방법: 1. 외부 클래스의 인스턴스 선언 2. 내부 클래스의 인스턴스 선언
        InnerExam1 t = new InnerExam1();
        Cal cal = t.new Cal();  // 외부에서는         InnerExam1.Cal cal = t.new Cal();
        cal.plus(3,2);
        System.out.println("cal.value = " + cal.value);
    }
}
