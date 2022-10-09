package com.moonz.javapractice.CrackingCodingInterview.ch7.prob2;

import lombok.Getter;
import lombok.Setter;

/*
 사용자로부터 걸려온 전화
 */
public class Call {
    @Getter
    @Setter
    private Rank rank; // 전화를 받는 최하위 직급
    private Caller caller;
    @Setter
    private Employee employee;

    public Call (Caller caller) {
        rank = Rank.RESPONDENT;
        this.caller = caller;
    }

    /*
    응답 메서드
     */
    public void reply(String msg) {
        System.out.println(msg);
    }

    /*
    전화받을 수 있는 직급 올리기
     */
    public void implementRank() {
        if (rank == Rank.RESPONDENT) rank = Rank.MANAGER;
        else if (rank == Rank.MANAGER) rank = Rank.DIRECTOR;
    }

    /*
    전화 끊기
     */
    public void disconnect() {
        System.out.println("전화가 종료되었습니다. 감사합니다.");
    }
}
