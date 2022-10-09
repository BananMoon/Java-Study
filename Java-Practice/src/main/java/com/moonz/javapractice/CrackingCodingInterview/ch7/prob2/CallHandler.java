package com.moonz.javapractice.CrackingCodingInterview.ch7.prob2;

import java.util.ArrayList;
import java.util.List;

/*
콜 전체를 관리하는 클래스
 */
public class CallHandler {
    private static final int RANKS = 3;
    private static final int NUM_RESPONDENT = 7;
    private static final int NUM_MANAGER = 5;
    private static final int NUM_DIRECTOR = 3;
    // 직급별 ~
    public List<List<Employee>> employeePerRank;    // 직원 리스트
    public List<List<Call>> callQueuesPerRank;   // 수신 전화 대기 큐

    public CallHandler() {
        employeePerRank = new ArrayList<>(RANKS);
        callQueuesPerRank = new ArrayList<>(RANKS);
        // respondent
        List<Employee> respondents = new ArrayList<>(NUM_RESPONDENT);
        for (int i=0; i< NUM_RESPONDENT; i++) {
            respondents.add(new Respondent(this));
        }
        employeePerRank.add(respondents);

        // manager
        List<Employee> managers = new ArrayList<>(NUM_MANAGER);
        for (int i=0; i< NUM_RESPONDENT; i++) {
            managers.add(new Manager(this));
        }
        employeePerRank.add(managers);

        // director
        List<Employee> directors = new ArrayList<>(NUM_DIRECTOR);
        for (int i=0; i< NUM_RESPONDENT; i++) {
            directors.add(new Director(this));
        }
        employeePerRank.add(directors);

        // callQueue init
        for (List<Call> calls : callQueuesPerRank) {
            calls = new ArrayList<>();
        }
    }
    /*
    첫번째로 가능한 직원 호출
     */
    public Employee getFirstPossibleEmployee (Call call) {
        for (int rank = 0; rank < RANKS; rank++) {
            List<Employee> employeesPerLevel = employeePerRank.get(rank);
            for (Employee emp : employeesPerLevel) {
                if (emp.isFree()) {
                    return emp;
                }
            }
        }
        return null;
    }

    /*
     전화 연결
      - 가능한 직원에게 배정한다.
      - 불가능한 경우 큐에 추가한다.
     */
    public void dispatchCall (Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }
    public void dispatchCall(Call call) {
        Employee freeEmp = getFirstPossibleEmployee(call);
        if (freeEmp == null) {
            call.reply("전화 가능한 직원이 응답하기까지 기다려주세요. 감사합니다.");
            callQueuesPerRank.get(call.getRank().getRankCode()).add(call);
        } else {
            freeEmp.receiveCall(call);
            call.setEmployee(freeEmp);
        }
    }

    /*
     직원이 전화받을 수 있는 상태가 되면, 응답할 전화를 찾을 때 호출한다.
     - 전화할당이 가능하면 true. 반대면 false
     - 자신의 rank와 일치하는 콜부터 아래 Rank로 내려갈 수도있다.
     */
    public boolean assignCall (Employee emp) {
        for (int rank = emp.rank.getRankCode(); rank >= 0; rank--) {
            List<Call> callsPerRank = callQueuesPerRank.get(rank);
            if (callsPerRank.size() > 0) {
                Call call = callsPerRank.remove(0);
                if (call != null) {
                    emp.receiveCall(call);
                    return true;
                }
            }
        }
        return false;
    }
}
