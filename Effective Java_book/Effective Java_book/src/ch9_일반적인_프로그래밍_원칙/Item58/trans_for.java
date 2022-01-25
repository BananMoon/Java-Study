package ch9_일반적인_프로그래밍_원칙.Item58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class trans_for {
    public static void main(String[] args) {
        List<String> c = new ArrayList<>(Arrays.asList("a", "b"));
        /*for (Iterator<String> i : c.iterator()); i.hasNext()) {

        } => 아예 for-each로 바꾸라고 컴파일러가 알려준다.*/
        for (String i : c) {
            System.out.println(i);
        }
    }

}
