package ch6_열거타입과_애너테이션.Item50;

import java.util.Date;

public class Period_basic {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시각
     * @param end 종료 시각
     * @throws IllegalArgumentException  시작시각이 종료시각보다 늦을 때 발생한다
     */
    public Period_basic(Date start, Date end) {
        // 유효성 검사
        if (start.compareTo(end) > 0)   // a.compareTo(b) : a가 b보다 크면 1 (thisTime<anotherTime ? -1 : (thisTime==anotherTime ? 0 : 1))
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");

        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public static void main(String[] args) {
        // 불변식 깨뜨려보기!
        Date start = new Date();
        Date end = new Date();
        System.out.println( "start = " + start + ", end = " + end);

        Period_basic p = new Period_basic(start, end);
        end.setTime(79);        // Date는 가변클래스이므로 p의 내부 수정 가능
        System.out.println("start = " + p.getStart() + ", 바뀐 end = " + p.getEnd());
    }
}
