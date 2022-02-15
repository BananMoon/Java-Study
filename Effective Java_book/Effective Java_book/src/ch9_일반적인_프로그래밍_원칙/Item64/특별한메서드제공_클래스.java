package ch9_일반적인_프로그래밍_원칙.Item64;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * 인터페이스가 아닌 클래스로 참조해야 하는 경우
 */
public class 특별한메서드제공_클래스 {

    static class Client {
        static void usingByteArrayInputStream (ByteArrayInputStream byteArrayInputStream) {

            // ByteArrayInputStream에만 있는 메서드
            System.out.println(byteArrayInputStream.read());
            System.out.println(byteArrayInputStream.read());
            byteArrayInputStream.mark(0);   // 30에서 마크
            System.out.println(byteArrayInputStream.read());
            System.out.println(byteArrayInputStream.read());
            System.out.println(byteArrayInputStream.read());
            byteArrayInputStream.reset();   // mark()한 그 자리로 다시 돌아간다.
            System.out.println(byteArrayInputStream.read());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        byte[] byteArray = new byte[]{10, 20, 30, 40, 50, 60};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        특별한메서드제공_클래스.Client.usingByteArrayInputStream(byteArrayInputStream);

        String filePath = "C:\\mysql-init.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
//        특별한메서드제공_클래스.Client.usingByteArrayInputStream(fileInputStream);

    }
}
