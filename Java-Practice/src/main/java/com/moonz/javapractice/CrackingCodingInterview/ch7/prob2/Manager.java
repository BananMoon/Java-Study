package com.moonz.javapractice.CrackingCodingInterview.ch7.prob2;

public class Manager extends Employee {
    public Manager(CallHandler callHandler) {
        super(callHandler);
        rank = Rank.MANAGER;
    }
}
