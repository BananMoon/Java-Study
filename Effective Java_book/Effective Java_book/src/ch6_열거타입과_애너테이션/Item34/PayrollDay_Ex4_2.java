package ch6_열거타입과_애너테이션.Item34;

//import static ch6_열거타입과_애너테이션.Item34.PayrollDay_Ex4_2.PayType.WEEKDAY;
//import static ch6_열거타입과_애너테이션.Item34.PayrollDay_Ex4_2.PayType.WEEKEND;

// 외부로도 뺼 수 있음.
enum PayType {
    WEEKDAY {
        private static final int MINS_PER_SHIFT =  8 * 60;

        int overtimePay(int minsWorked, int payRate) {
            return minsWorked <=  MINS_PER_SHIFT ?
                    0 : (minsWorked - MINS_PER_SHIFT) * payRate/2;  // 오버타임이 발생하면 오버타임 * 기본 임금/2
        }
    },
    WEEKEND {
        int overtimePay(int minsWorked, int payRate) {
            return minsWorked* payRate / 2;
        }
    };
    abstract int overtimePay(int minsWorked, int payRate);  // 그날 일한 시간, 직원의 (시간당) 기본 임금

    // 전체 페이 메서드
    int pay(int minsWorked, int payRate) {
        int basePay = minsWorked * payRate;
        return basePay + overtimePay(minsWorked, payRate);  // 기본 페이 + 오버타임 수당 페이
    }
}
enum PayrollDay_Ex4_2 {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;  // static은 무조건 초기화해야함.
    PayrollDay_Ex4_2(PayType payType) { this.payType = payType; }    // 생성자

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }
    public static void main(String[] args) {
        int  minutesWorked = 10*60; // 10시간 (2시간 오버타임)
        int payRate = 10000;    // 시급 만원
        for (PayrollDay_Ex4_2 pd : PayrollDay_Ex4_2.values())
            System.out.printf("시급이 %d원인 A씨는 %s에 %d분 근무하여 %d원 받습니다.\n", payRate, pd.toString(), minutesWorked, pd.pay(minutesWorked,payRate));
    }
}
