package ch6_열거타입과_애너테이션.Item45;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Anagrams_Stream {

    public static void main(String[] args) throws IOException {     // Files.lines()에 의해 줄바꿈 기호가 제거되어 파일 읽는동안 IOException이 발생?
        Path dictionary = Paths.get("C:\\example.txt"); // 특정 경로의 파일 정보를 가져온다.
        int minGroupSize = Integer.parseInt("2");   // 값이 2개 이상

        // 1. try-with-resources 블록에서 dictionary 오픈
        try (Stream<String> words = Files.lines(dictionary)) {  // 2, Files 클래스의 lines() : 파일에서 모든 line을 읽어서 Stream으로 리턴
            words.collect(groupingBy(word -> alphabetize(word)))    // 각 단어들을 알파벳 정렬하고 그룹핑하여 collection -> 맵
                    .values().stream()                                                      // 3. List<String> 원소로 구성된 스트림 생성 : Stream<List<String>>
                    .filter(group -> group.size() >= minGroupSize)  // 4. 리스트들 중 원소갯수가 minGroupSize보다 적은 것은 필터링되어 무시.
                    .forEach(group -> System.out.println("그룹 크기가 "+ group.size() + "인 : " + group)); // 5. 종단 연산. 살아남은 리스트와 size  출력
        }
    }

    // 단어를 받아서 알파벳 정렬 후 다시 String으로 반환 메서드
    private static String alphabetize(String s) {
        char[] a = s.toCharArray(); // 알파벳 배열
        Arrays.sort(a);     // 알파벳 정렬
        String sortedStr = new String(a);
        System.out.println("정렬된 string: "+ sortedStr);  // 정렬된 String
        return sortedStr;
    }
}
