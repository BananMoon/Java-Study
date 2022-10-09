package com.moonz.javapractice.CrackingCodingInterview.ch7.prob2;

import java.util.List;

public abstract class Employee {
    private Call currentCall = null;    // 수정 불가
    protected Rank rank;
    private CallHandler callHandler;    /* 추가: 수정 불가 */

    public Employee(CallHandler callHandler) {
        List<Employee> employeesAtSameRank = callHandler.employeePerRank.get(rank.getRankCode());
        employeesAtSameRank.add(this);
    }
    /*
    상담 가능한지
     */
    public boolean isFree() {
        return currentCall == null;
    }
    /*
    상담 시작
     */
    public void receiveCall(Call call) {
        currentCall = call;
    }
    /*
    문제 해결 후 상담 종료
    - 새 콜을 할당받는 메서드 호출한다.
     */
    public void resolved() {
        if (currentCall != null) {
            currentCall.disconnect();
            currentCall = null;
        }
        assignNewCall();
    }
    /*
    전화가능한 경우, 새 전화 할당
     */
    protected void assignNewCall() {
        if (isFree()) {
            callHandler.assignCall(this);
        }
    }

    /*
    문제 해결 실패
     */
    public void resolveFailed() {
        linkToHigerRank();
        reassign();
    }
    /*
    문제 해결되지 않을 경우, 상위 직급으로 배정하고 call을 재 할당해야 한다.
     */
    public void linkToHigerRank() {
        // linkToHigerRank
        if (currentCall == null) return;
        currentCall.implementRank();
        callHandler.dispatchCall(currentCall);

        currentCall = null;
    }

    public void reassign() {
        callHandler.assignCall(this);
    }
}
