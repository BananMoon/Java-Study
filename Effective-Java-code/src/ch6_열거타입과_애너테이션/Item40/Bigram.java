package ch6_열거타입과_애너테이션.Item40;

import java.util.HashSet;
import java.util.Set;

public class Bigram {
    private final char first;
    private final char second;
    public Bigram (char first, char second) {
        this.first = first;
        this.second = second;
    }
    // Object의 equals는 매개변수가 Object 이므로 재정의가 아닌 다중정의를 한것으로 여겨지므로 @Override를 통해 재정의임을 명시한다.
    // 왜 에러뜨지?
//    @Override
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }

    public int hashCode() {
        return 31*first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i=0; i<10; i++) {
            for (char ch='a'; ch<='z' ; ch++)
                 s.add(new Bigram(ch, ch));
            System.out.println(s.size());
        }
    }
}
