package Ch3_모든객체의_공통메서드.Item15.team;

public class Developer {
    public final Plan plan = new Plan();
    public void programming() {
        plan.makeCodingPlan();
        System.out.println("코드 구현");
    }
    // 내부 클래스. public -> private으로 변경해도 됨.
    private class Plan {
        public void makeCodingPlan() {
            System.out.println("프로그래밍 계획 세우기");
        }
}
}
