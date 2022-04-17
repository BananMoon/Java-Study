package com.moonz.javapractice.Object;

public class MainConstructor {
    static class Cash {
        private int dollars;
        Cash (float dlr) {
            this((int)dlr);
//             this.dollars = (int)dlr;   // Nope!!!! dollars 값에 대한 유효성 검사 로직을 작성해야한다면, 세곳 모두 수정해야합니다.
        }
        Cash (String dlr) {
            this(Cash.parse(dlr));
        }
        Cash (int dlr) {
            this.dollars = dlr;
        }

        private static int parse(String s) {
            return Integer.parseInt(s);
        }
    }
}
