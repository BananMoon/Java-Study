package Ch3_모든객체의_공통메서드.Item15.team;

// 외부 패키지에서 상속받고 있는데,
// protected로 하게되면 같은 클래스&같은 패키지&자식클래스까지만 가능. 그외 불가.
public class Life {
    public void eat() {
        cook();
        System.out.println("먹기");
    }
    private void cook() {    // cook()은 외부에서 사용하지 않으니(내부에서만 사용) private으로 만들어 API를 공개하지 x
        System.out.println("요리하기");
    }
}
