package Ch3_모든객체의_공통메서드.Item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class String_Compare_Ex1 {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, "b", "a");        // 자동으로 알파벳 순으로 정렬되어 등록됨
        System.out.println(s);      // [a, b]
    }

}
