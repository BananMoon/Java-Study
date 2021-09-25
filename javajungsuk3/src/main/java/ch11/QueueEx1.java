package ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class QueueEx1 {
    static Queue q = new LinkedList();
    static final int MAX_SIZE = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String typing;
        System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");
        while(true)  {
            System.out.print(">>");

            try {
                typing = br.readLine();
                if ("".equals(typing)) continue;

                if (typing.equalsIgnoreCase("q")) {
                    System.exit(0);
                } else if (typing.equalsIgnoreCase("help")) {
                    System.out.println("help - 도움말을 보여줍니다.");
                    System.out.println("q 또는 Q - 프로그램을 종료합니다.");
                    System.out.println("history - 최근에 입력한 명령어를 "+MAX_SIZE+ "개 보여줍니다.");
                } else if (typing.equalsIgnoreCase("history")) {
                    //입력받은 명령어 저장하기
                    save(typing);
                    //LinkedList 내용 5개 다 보여주기.
                    //임시
                    LinkedList tmp = (LinkedList)q; // Queue에는 메서드가 부족(6개)하니까 linkedlist로 바꾸면 더 많은 기능 사용 가능!
                    final int SIZE = tmp.size();
                    for (int i=0; i<SIZE; i++) {
                        System.out.println((i+1) + ".  "+ tmp.get(i));
                    }

                    // ListIterator를 잘 안쓰
//                    ListIterator it = tmp.listIterator();
//
//                    while(it.hasNext()) {
//                        System.out.println(++i + ". " + it.next());
//                    }
                } else {
                    save(typing);
                    System.out.println(typing);
                }
            } catch (Exception e) {
                System.out.println("입력 오류입니다.");
            }
        }
    }
    public static void save(String typing) {
        if (!"".equals(typing)) {   //!typing.equals("")이 아닌 이유: typing에 null이 들어가게되면 error 발생하니까!
            q.offer(typing);
        }
        //큐에 5개 쌓였는지 확인
        if (q.size() > MAX_SIZE) {
            q.remove();
        }
    }
}
