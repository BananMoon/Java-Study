package ch6_열거타입과_애너테이션.Item34;

public enum PayrollDay_Ex4_1 {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY, SUNDAY;

    private static final int MINS_PER_SHIFT = 8 * 60;

    int pay(int minutesWorked, int payRate) {       // 그날 일한 시간, 직원의 (시간당) 기본 임금
        int basePay = minutesWorked * payRate;
        int overtimePay;    // 잔업 수당

        switch(this) {
            case SATURDAY: case SUNDAY: // 주말: 무조건 잔업수당이 주어짐
                overtimePay = basePay / 2;
                break;
            default:     // 주중 : 오버타임 발생 시 잔업수당이 주어짐
                overtimePay = minutesWorked <=  MINS_PER_SHIFT ?// 그날 일한 시간(minutesWorked)이 8시간 이상이면 기본 8시간을 뺸 시간에 대해 잔업수당이 주어진다.
                        0 : (minutesWorked-MINS_PER_SHIFT) * payRate/2;   // 잔업수당은 기본 임금의 50%
        }
        return basePay + overtimePay;   // 기본 시간에 대한 pay + 잔업 수당에 대한 pay
    }
}
