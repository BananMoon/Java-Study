package ch11;
import java.util.*;

public class ExpValidCheck {
    public static void main(String[] args) {
        if (args.length != 1) {    // 아무것도 입력안한 경우
            System.out.println("Usage    :  java ExpValidCheck \"EXPRESSION\"");
            System.out.println("Example :  java ExpValidCheck \"((2+3)*1)+3\"");
            System.exit(0);
        }
        //입력한 경우
        Stack st = new Stack();
        String expression = args[0];
        System.out.println("expression: " + expression);

        try {
            for (int i = 0; i < expression.length(); i++) {
                //String에서 index로 값 접근방법 charAt()
                char ch = expression.charAt(i);
                if (ch =='(') {
                    st.push(ch+""); // 문자열로 push해야하나?
                } else if(ch==')') {
                    st.pop();
                }
            }
            if (st.isEmpty()) {
                System.out.println("correct!");
            } else {
                System.out.println("failed!");
            }
        } catch (EmptyStackException e) {   //')'를 만나서 '('를 꺼내려할 때 스택이 비어있으면 EmptyStackException 오류
            System.out.println("failed! Exception!");
        }
    }
}