package comuStudy_days10;

import java.util.Scanner;

public class day9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=====개발자 MBTI 결과 보기======");
        System.out.print("당신의 MBTI를 입력하세요 : ");
        String result = sc.next();

        if (result.equals("INTP") || result.equals("intp")) {
            System.out.println("당신은 백엔드형입니다!");
        } else if (result.equals("ENFJ") || result.equals("enfj")) {
            System.out.println("당신은 프론트엔드형입니다!");
        } else if (result.equals("INFJ") || result.equals("infj")) {
            System.out.println("당신은 풀스택형입니다!");
        } else if (result.equals("ISTJ") || result.equals("istj")) {
            System.out.println("당신은 퍼블리셔형입니다!");
        } else if (result.equals("ENTJ) || result.equals(entj")) {
            System.out.println("당신은 아키텍쳐형입니다!");
        } else if (result.equals("ISFJ") || result.equals("isfj")) {
            System.out.println("당신은 보안전문가형입니다!");
        } else if (result.equals("INTJ") || result.equals("intj")) {
            System.out.println("당신은 데이터분석가형입니다!");
        } else if (result.equals("ENFP") || result.equals("enfp")) {
            System.out.println("당신은 AI형입니다!");
        } else if (result.equals("ENTP") || result.equals("entp")) {
            System.out.println("당신은 iOS형입니다!");
        } else if (result.equals("ESFJ") || result.equals("esfj")) {
            System.out.println("당신은 안드로이드형입니다!");
        } else if (result.equals("ESFP") || result.equals("esfp")) {
            System.out.println("당신은 게임개발자형입니다!");
        } else if (result.equals("ESTP") || result.equals("estp")) {
            System.out.println("당신은 IoT 개발형입니다!");
        } else if (result.equals("ESTJ") || result.equals("estj")) {
            System.out.println("당신은 QA형입니다!");
        } else if (result.equals("INFP") || result.equals("infp")) {
            System.out.println("당신은 블록체인형입니다!");
        } else if (result.equals("ISTP") || result.equals("istp")) {
            System.out.println("당신은 임베디드 개발자형입니다!");
        } else if (result.equals("ISFP") || result.equals("isfp")) {
            System.out.println("당신은 네트워크 개발자형입니다!");
        } else {
            System.out.println("정확한 MBTI 유형이 아닙니다.");
        }
    }
}