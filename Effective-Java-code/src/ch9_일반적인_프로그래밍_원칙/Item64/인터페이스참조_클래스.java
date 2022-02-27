package ch9_일반적인_프로그래밍_원칙.Item64;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 인터페이스를 참조하면 구현체만 바꾸어도 이를 이용하는 클라이언트에게 아무런 영향을 미치지 않는다.
 */
public class 인터페이스참조_클래스 {

    static class Client {
        static void usingInputStream (InputStream inputStream) throws IOException {
            // InputStream을 구현하는 클래스에는 공통적으로 존재하는 메서드
            System.out.println(inputStream.read());
            System.out.println(inputStream.read());
            long skip = inputStream.skip(2);
            System.out.println(skip+ "개 스킵");
            System.out.print("남은 데이터 갯수 : ");
            System.out.println(inputStream.available());
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] byteArray = new byte[]{10, 20, 30, 40, 50, 60};
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        인터페이스참조_클래스.Client.usingInputStream(byteArrayInputStream);

        String filePath = "C:\\mysql-init.txt";
        InputStream fileInputStream = new FileInputStream(filePath);
        인터페이스참조_클래스.Client.usingInputStream(fileInputStream);
    }
}
