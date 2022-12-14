package com.moonz.javapractice.CrackingCodingInterview.ch10.prob7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/** 빠트린 정수
 * 제한 : 1GB 메모리, 음이 아닌 정수 40억개로 이루어진 입력 파일
 * 메모리 1GB = 8 & 2^30 = 80억 비트를 제공하므로, 모든 정수값을 메모리의 각 비트에 대응시킬 수 있다.
 *
 * 1. 40억 크기의 비트벡터 생성 후 0으로 초기화
 * 2. 파일을 읽어나가면서 1로 세팅
 * 3. 비트벡터의 인덱스 0부터 읽으면서 값이 0인 인덱스를 출력
 */
public class prob7_1 {
    // TODO 왜 long 만든 후에 8로 나누지?
    long numberOfInts = ((long)Integer.MAX_VALUE) + 1;
    byte[] bv = new byte[(int)(numberOfInts / 8)];
    String filename = "CrackingCodingInterview/ch10/prob7/input.txt";
    void findMissedNumber () throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(filename));
        while (sc.hasNextInt()) {   // 값이 있을 동안만 진행
            int num = sc.nextInt();

        }
    }
}
