package com.moonz.javapractice.DesignPattern.state;

// 추가!
// 1. 상태를 implements한다. (만약 State 인터페이스를 상속받는 추상클래스 MoneyState를 extends하면 선택적으로 오버라이드할 수 있음)
// 2. 메서드를 오버라이드한다.
// 3. 상태를 나타내는 인스턴스를 미리 생성해놓고 해당 인스턴스를 리턴하도록 한다. (singleton)
public class RICH implements State{
    // 싱글톤
    private static final RICH rich = new RICH();
    private RICH() { };  // 생성자는 private으로

    public static RICH getInstance() {  // 객체 getter을 public으로
        return rich;
    }

    @Override
    public State getMoney() {
        return this;
    }
    @Override
    public State buySomething() {
        return WEALTHY.getInstance();
    }

    @Override
    public void pleaseGiveMeYourMoney() {
        System.out.println("당근이지~ 나 부자야");
    }

    @Override
    public void printCurrentMoneyState() {
        System.out.println("부자인 상태");
    }
}
