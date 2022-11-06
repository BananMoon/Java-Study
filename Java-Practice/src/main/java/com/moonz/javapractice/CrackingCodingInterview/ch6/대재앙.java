package com.moonz.javapractice.crackingCodingInterview.ch6;
import java.util.Random;

public class 대재앙 {
    public static void main(String[] args) {
        System.out.println(getPercentOfNFamilies(100));
    }
    static double getPercentOfNFamilies(int n) {
        int totalGirlCnt = 0;
        int totalBoyCnt = 0;
        for (int i=0; i<n; i++) {
            int[] genders = runOneFamily();
            totalGirlCnt += genders[0];
            totalBoyCnt += genders[1];
        }
        return (double)totalGirlCnt / ( totalGirlCnt + totalBoyCnt);
    }

    static int[] runOneFamily() {
        Random random = new Random();
        int boyCnt = 0;
        int girlCnt = 0;
        //여자 출산까지
        while (girlCnt == 0) {
            if (random.nextBoolean()) {   // boolean형 난수(0,1) 반환
                girlCnt++;
            }
            else {
                boyCnt++;
            }
        }
        return new int[]{girlCnt, boyCnt};
    }
}