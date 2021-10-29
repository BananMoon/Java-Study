package Item4;

public final class UtilClass {

    // 3. private 생성자
    // 3-1. 인스턴스 생성 X
    // 3-2. 상속 X
    private UtilClass() {
        throw new AssertionError();     // 클래스 안에서 실수로라도 생성자를 호출하지 않기 위해 생성자 내에 Assertion Error 추가
    }

    public static String getName() {
        return "jaegeun";
    }

    public static void main(String[] args) {

//		String test = UtilClass.getName();
//
//		System.out.println(test);


        // 1. abstract 클래스로 instance 생성 막기
        // 추상 클래스는 인스턴스를 생성할 수 없다.
        // 자체적으로 객체를 생성할 수 없다.
        // 따라서 상속을 통해 자식 클래스에서 인스턴스를 생성해야 한다.
        UtilClass utilClass = new UtilClass();
//		System.out.println(utilClass.getName());


        // 2. abstract, 상속
//		UtilClass2 utilClass2 = new UtilClass2();
//		System.out.println(utilClass2.getName());
    }

    static class UtilClass2 extends UtilClass{

    }
}