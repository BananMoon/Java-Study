package ch7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 익명 클래스로 변환하기 전
public class Ex7_18 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new EventHandler());    // EventHandler클래스 객체 생성

        // 일회성 클래스를 익명 클래스로 만들고 내부에는 구현해야하는 메서드를 오버라이드한다.
        // 클래스 정의와 객체 생성을 동시에
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("익명클래스로 ActionListener인터페이스의 메서드 actionPerformed 실행!!");
            }
        });
    }
}
// 일회성으로 쓰이는 클래스
class EventHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("ActionEvent occurred!!");
    }
}
