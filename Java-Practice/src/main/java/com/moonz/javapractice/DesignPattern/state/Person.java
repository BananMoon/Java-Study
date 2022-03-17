package com.moonz.javapractice.DesignPattern.state;

public class Person {
    private State state;
    public Person (State state) {
        this.state =state;
    }
    public void getMoney() {
        state = state.getMoney();
        state.printCurrentMoneyState();
    }
    public void buySomething() {
        state = state.buySomething();
        state.printCurrentMoneyState();
    }

    public void pleaseGiveMeYourMoney() {
        state.pleaseGiveMeYourMoney();
    }

    public void printCurrentMoneyState() {
        state.printCurrentMoneyState();
    }
}
