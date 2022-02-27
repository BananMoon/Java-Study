package ch6_열거타입과_애너테이션.Item36;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Text {
    public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

    // 어떤 Set을 넘겨도 좋으나 EnumSet이 가장 좋다.
    public void applyStryles (Set<Style> styles) {
        System.out.printf("Applying styles %s to text%n",
                Objects.requireNonNull(styles));
    }
    // 사용 예
    public static void main(String[] args) {
        Text text = new Text();
        text.applyStryles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
