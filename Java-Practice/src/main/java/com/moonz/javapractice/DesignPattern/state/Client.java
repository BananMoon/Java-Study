package com.moonz.javapractice.DesignPattern.state;

public class Client {
    public static void main(String[] args) {
        Person person = new Person(WEALTHY.getInstance());
        person.printCurrentMoneyState();
        person.buySomething();  // POOR -> POOR, WEALTHY -> POOR
        person.getMoney();  // WEALTHY -> WEALTHY, POOR -> WEALTHY
        person.printCurrentMoneyState();
        person.pleaseGiveMeYourMoney();
    }
}
