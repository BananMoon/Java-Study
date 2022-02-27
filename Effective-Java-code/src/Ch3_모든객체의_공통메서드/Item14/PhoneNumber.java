package Ch3_모든객체의_공통메서드.Item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class PhoneNumber implements Comparable<PhoneNumber> {
    Integer areaCode;
    Integer prefix;
    Integer lineNum;

    public PhoneNumber(Integer areaCode, Integer prefix, Integer lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    @Override
    public int compareTo(PhoneNumber pn) {
        // 중요도 : areaCode > prefix > lineNum
        int result = Integer.compare(areaCode, pn.areaCode);
        if (result == 0) {
            result = Integer.compare(prefix, pn.prefix);
            if (result == 0)
                result = Integer.compare(lineNum, pn.lineNum);
        }
        return result;
    }

    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber(10, 4, 3);
        PhoneNumber pn1 = new PhoneNumber(10, 1, 1);
        PhoneNumber pn2 = new PhoneNumber(10, 3, 1);

        Set<PhoneNumber> s = new TreeSet<>();
        Collections.addAll(s, pn, pn1, pn2);
        System.out.println(s.size());
    }
}
