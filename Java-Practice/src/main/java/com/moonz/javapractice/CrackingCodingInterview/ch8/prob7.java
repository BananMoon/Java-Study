package com.moonz.javapractice.CrackingCodingInterview.ch8;

import java.util.List;
import java.util.ArrayList;
/* Q. 중복 없는 순열.
input에서 n개 만큼 고르는데 순서가 고려되는 순열의 모든 경우의 수  (문자 중복이 없어야 한다.)
*/
public class prob7 {
    public static void main(String[] args) {
        getPermutation("강산단란", 3);
    }
    private static final List<String> answers = new ArrayList<>();
    private static boolean[] visited;
    public static void getPermutation(String input, int n) {
        visited = new boolean[input.length()];
        getPermutation(input, 0, n, "");
        for (String str: answers) {
            System.out.println(str);
        }
    }
    private static void getPermutation(String input, int depth, int length, String curr) {
        if (depth == length) {
            answers.add(curr);
            return;
        }

        for (int i=0; i<input.length(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            getPermutation(input, depth+1, length, curr + input.charAt(i));
            visited[i] = false;
        }
    }
}