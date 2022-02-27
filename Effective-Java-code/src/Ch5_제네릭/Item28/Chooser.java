package Ch5_제네릭.Item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 반환된 Object를 원하는 타입으로 형변환해야하는데, 만약 타입이 다른 원소가 들어있다면 런타임에 형변환 오류
 * 다른 타입원소 넣었는데 왜 오류가 안나지? ★★★
 */
public class Chooser {
    private final Object[] choiceArray;
    private String shape;
    private int num;

    public Chooser(Collection choices) {
        choiceArray = choices.toArray();
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();   // 현재 thread's ThreadLocalRandom을 반환
        return choiceArray[rnd.nextInt(choiceArray.length)];    // Object 배열 반환
    }
    // -> Object 배열로 반환하므로 원하는 타입으로 형변환해야하는데, 만약 다른 타입이 들어가 있다면 런타임에 형변환 오류가 발생다.


    public Chooser(Collection choices, String shape, int num) {
        this.choiceArray = choices.toArray();
        this.shape = shape;
        this.num = num;
    }

    public String getShape() {
        return shape;
    }

    public int getNum() {
        return num;
    }
    public static void main(String[] args) {
        List<Student> listStud = new ArrayList<>();
        List<Chooser> listChooser = new ArrayList<>();
// error 발생, 타입이 다른 원소가 들어가면 런타임에 형변환 오류!
//        listChooser.add(new Student("문윤지", 201835652));
        listStud.add(new Student("아무개", 201940650));
        Chooser ch = new Chooser(listStud);
        System.out.println("Chooser 클래스에서 : "+ ch.choose());

    }
}
