package com.moonz.javapractice.CrackingCodingInterview.ch7.prob2;

public class Director extends Employee {
    public Director(CallHandler callHandler) {
        super(callHandler);
        rank = Rank.DIRECTOR;
    }
}
