package com.moonz.javapractice.DesignPattern.state;

public class WEALTHY implements State{
    // 싱글톤
    private static final WEALTHY wealthy = new WEALTHY();
    private WEALTHY() { }

    public static WEALTHY getInstance() {
        return wealthy;
    }

    @Override
    public void pleaseGiveMeYourMoney() {
        System.out.println("돈이 넉넉하니 줄게!!");
    }

    @Override
    public void printCurrentMoneyState() {
        System.out.println("돈이 많은 상태");
    }
    @Override
    public State getMoney() {
        return RICH.getInstance();
    }

    @Override
    public State buySomething() {
        return POOR.getInstance();
    }
}
