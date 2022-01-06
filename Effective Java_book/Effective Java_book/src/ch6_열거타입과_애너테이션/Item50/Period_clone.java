package ch6_열거타입과_애너테이션.Item50;

import java.util.Date;

public class Period_clone {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시각
     * @param end 종료 시각
     * @throws IllegalArgumentException  시작시각이 종료시각보다 늦을 때 발생한다
     */
    public Period_clone(Date start, Date end) {
        // 1. 방어적 복사 : 매개변수의 복사본(인스턴스) 새로 생성
        this.start = clone(start.getTime());    // gateTime() :  January 1, 1970, 00:00:00 GMT부터 주어진 Date까지의 milliseconds 반환
        this.end = clone(end.getTime());

        // 2. 위에서 생성한 복사본으로 유효성 검사
        if (this.start.compareTo(this.end) > 0)   // a.compareTo(b) : a가 b보다 크면 1 (thisTime<anotherTime ? -1 : (thisTime==anotherTime ? 0 : 1))
            throw new IllegalArgumentException(this.start + "가 " + this.end + "보다 늦다.");

    }

    private Date clone(long time) {
        return new Date(time);
    }
    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
    public static void main(String[] args) {
        Period_clone period_clone = new Period_clone(new Date(), new Date());
        System.out.println( "start = " + period_clone.getStart() + ", end = " + period_clone.getEnd());

        공격Child c = new 공격Child(new Date(), new Date());
        System.out.println("start = " + c.getStart() + ", 바뀐 end = " + c.getEnd());

    }

}

class 공격Child extends Period_clone {
    private Date start;
    private Date end;

    public 공격Child(Date start, Date end) {
        super(start, end);
        this.start = start;
        this.end = end;
    }

    public Date clone() {
        Date d = new Date(10);  // 20 :  Thu Jan 01 09:00:00 KST 1970
        System.out.println("child의 clone 호출");
        return d;
    }
    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}