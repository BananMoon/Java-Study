package com.moonz.javapractice.CrackingCodingInterview.ch8;
import java.util.*;

/*
알맞은 n쌍의 괄호 만들기
if문을 통해 문제되는 상황을 제거하고,
'(', ')' 하나씩 추가해가므로 특정 인덱스를 반복할 일 X -> 각 문자열이 유일함을 보장
*/
public class prob9 {
    public static void main(String[] args) {
        List<String> answers = generateParens(3);
        for (String a : answers) {
            System.out.println(a);
        }
    }

    public static void addParen(List<String> answers, char[] str, int leftCnt, int rightCnt, int idx) {
        if (leftCnt < 0 || leftCnt > rightCnt) return;  // ()) : left 2, right1개 남은 상황 -> X

        if (leftCnt == 0 && rightCnt == 0) {
            answers.add(String.copyValueOf(str));
        } else {
            // left 추가
            str[idx] = '(';
            addParen(answers, str, leftCnt-1, rightCnt, idx+1);
            // right 추가
            str[idx] = ')';
            addParen(answers, str, leftCnt, rightCnt-1, idx+1);
        }
    }
    public static List<String> generateParens(int n) {
        char[] arr= new char[n*2];
        List<String> answers = new ArrayList<>();
        addParen(answers, arr, n, n, 0);
        return answers;
    }
}