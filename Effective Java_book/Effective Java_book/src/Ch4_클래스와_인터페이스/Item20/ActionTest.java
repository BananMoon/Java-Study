package Ch4_클래스와_인터페이스.Item20;

public class ActionTest {
    public static void main(String[] args) {
        Action action = new MyAction(); // 추상클래스인 Action은 인스턴스 생성이 안되므로 자식클래스로 생성
        action.exec();

        // 익명 클래스는 자식 클래스인 MyAction 클래스를 사용하지 않고, Action 클래스를 상속받은 익명 클래스를 만들어서 바로 쓸 수 있도록 하는 방식
        Action anonymousAction = new Action() { // 익명으로 클래스가 만들어짐.
            @Override
            public void exec() {
                System.out.println("익명 클래스에서 exec 재정의");
            }
        };  // new Action() 생성자 다음의 중괄호 {} : 해당 생성자 이름(Action)에 해당하는 클래스를 상속받은 익명 객체를 만들면서 괄호 안에서 메서드를 구현한다. (메서드 추가도 가능)
        // 이름 없는 객체를 anonymousAction 참조변수가 참조하도록 하였다
        anonymousAction.exec();
        //Action을 상속받는 클래스를 굳이 만들어낼 필요가 없을 때. 즉 해당 클래스 내에서만 한번 사용이 될 때 익명 중첩 클래스를 사용한다.
    }
}
