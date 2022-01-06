package ch6_열거타입과_애너테이션.Item50;

import java.util.Date;

public class Period_DefensiveCopy {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시각
     * @param end   종료 시각
     * @throws IllegalArgumentException 시작시각이 종료시각보다 늦을 때 발생한다
     */
    public Period_DefensiveCopy(Date start, Date end) {
        // 1. 방어적 복사 : 매개변수의 복사본(인스턴스) 새로 생성
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        // 2. 위에서 생성한 복사본으로 유효성 검사
        if (this.start.compareTo(this.end) > 0)   // a.compareTo(b) : a가 b보다 크면 1 (thisTime<anotherTime ? -1 : (thisTime==anotherTime ? 0 : 1))
            throw new IllegalArgumentException(this.start + "가 " + this.end + "보다 늦다.");

    }

    public Date getStart() {
        return start;
//         return new Date(start.getTime());   // 방어적 복사
    }

    public Date getEnd() {
        return end;
        // return new Date(end.getTime());  // 방어적 복사
    }

    public static void main(String[] args) {
        // 불변식 깨뜨려보기!
        Date start = new Date();
        Date end = new Date();
        System.out.println("start = " + start + ", end = " + end);
        Period_DefensiveCopy p = new Period_DefensiveCopy(start, end);

        System.out.println("첫 번째 공격 ========================");
        end.setTime(79);
        System.out.println("p.start = " + p.getStart() + ", 바뀌지않는 p.end = " + p.getEnd());

        System.out.println("두 번째 공격 ========================");
        Period_DefensiveCopy p2 = new Period_DefensiveCopy(start, end);
        p2.getEnd().setYear(78);
        System.out.println("p2.start = " + p.getStart() + ", 바뀌는 p2.end = " + p.getEnd());
    }
}
