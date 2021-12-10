package ch6_열거타입과_애너테이션.Item34;

import static ch6_열거타입과_애너테이션.Item34.PayrollDay_Ex4_2.PayType.WEEKDAY;
import static ch6_열거타입과_애너테이션.Item34.PayrollDay_Ex4_2.PayType.WEEKEND;

enum PayrollDay_Ex4_2 {
    MONDAY(WEEKDAY), TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY), THURSDAY(WEEKDAY), FRIDAY(WEEKDAY),
    SATURDAY(WEEKEND), SUNDAY(WEEKEND);

    private final PayType payType;  // static은 무조건 초기화해야함.
    PayrollDay_Ex4_2(PayType payType) { this.payType = payType; }    // 생성자

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }
    enum PayType {
        WEEKDAY {
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
        private static final int MINS_PER_SHIFT = 8 * 60;

        // 전체 페이 메서드
        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);  // 기본 페이 + 오버타임 수당 페이
        }
    }
}
