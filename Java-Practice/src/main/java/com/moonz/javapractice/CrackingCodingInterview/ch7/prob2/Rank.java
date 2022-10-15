package com.moonz.javapractice.CrackingCodingInterview.ch7.prob2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank {
    RESPONDENT(0),
    MANAGER(1),
    DIRECTOR(1),
    ;

    private int rankCode;
    public int getRankCode() {
        return rankCode;
    }
}
