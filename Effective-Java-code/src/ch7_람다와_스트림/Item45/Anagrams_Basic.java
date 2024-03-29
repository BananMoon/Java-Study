package ch7_람다와_스트림.Item45;

import java.io.File;
import java.io.IOException;
import java.util.*;

// 사전 하나를 훑어 원소 수가 많은 아나그램 그룹들을 출력
public class Anagrams_Basic {
    public static void main(String[] args) throws IOException {
        File dictionary = new File("C:\\example.txt");   // args[0]
        int minGroupSize = Integer.parseInt("2");   // 값이 2개 이상

        Map<String, Set<String>> groups = new HashMap<>();  // 1: apple, banana
        // 1. try-with-resources 블록에서 dictionary 오픈
        try (Scanner s =new Scanner(dictionary)) { //aelpp : Set<"apple, appel
            while (s.hasNext()) {
                String word = s.next(); // String 한개 씩 저장
                groups.computeIfAbsent(alphabetize(word),     // (key, mappingFunction) : map에서 해당 단어가 key로 존재하는지 체크해서 있으면  그 키에 매핑된 값을 반환,
                        (unused)-> new TreeSet<>())    // 없으면 함수 객체를 키에 적용해서 값을 계산해낸 다음 키와 값을 매핑 하는 메서드
                        .add(word);                                    // -> 키가 없으면 값을 담을 TreeSet을 생성한 후, 해당 word 변수(apple)를 키값 'aelpp'에 해당하는 set에 추가.
            }
        }
        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + " : " + group);
        }
    }
    // 단어를 받아서 알파벳 정렬 후 다시 String으로 반환 메서드
    private static String alphabetize(String s) {   // apple -> a, p, p, l, e -> aelpp
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
