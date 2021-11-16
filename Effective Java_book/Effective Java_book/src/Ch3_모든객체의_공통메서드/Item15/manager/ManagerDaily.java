package Ch3_모든객체의_공통메서드.Item15.manager;

import Ch3_모든객체의_공통메서드.Item15.team.Developer;
import Ch3_모든객체의_공통메서드.Item15.team.Life;

public class ManagerDaily extends Life {
    public static void main(String[] args) {
        ManagerDaily managerDaily = new ManagerDaily();
        Developer dev = new Developer();

        managerDaily.eat();

        System.out.println("------\n 개발 요청");
        dev.programming();
    }
}
