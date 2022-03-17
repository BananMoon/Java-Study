package com.moonz.javapractice.DesignPattern.state;

public class POOR implements State {
    // 싱글톤
    private static final POOR poor = new POOR();
    private POOR() { };  // 생성자는 private으로

    public static POOR getInstance() {  // 객체 getter을 public으로
        return poor;
    }

    @Override
    public void pleaseGiveMeYourMoney() {
        System.out.println("돈이 없어!!");    // 더 거지가 될수 없음.
    }

    @Override
    public void printCurrentMoneyState() {
        System.out.println("돈이 없는 상태");
    }

    @Override
    public State getMoney() {
        return WEALTHY.getInstance();
    }

    @Override
    public State buySomething() {
        return this;
    }
}
