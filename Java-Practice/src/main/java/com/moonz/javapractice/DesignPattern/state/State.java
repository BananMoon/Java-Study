package com.moonz.javapractice.DesignPattern.state;
// 빛의 상태를 상속하는 상태 인터페이스
// 상태에 따라 다르게 구현될 인터페이스
public interface State {

    State getMoney();
    State buySomething();

    void pleaseGiveMeYourMoney();
    void printCurrentMoneyState();
}
