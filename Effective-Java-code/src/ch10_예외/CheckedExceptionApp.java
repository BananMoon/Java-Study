package ch10_예외;

import java.io.FileWriter;
import java.io.IOException;

public class CheckedExceptionApp {
    public static void main(String[] args) {
        ioException();  // 가독성이 떨어진다.
        ioExceptionWithTryResource();
    }
    private static void ioException() {
        // Unhandled exception: java.io.IOException
        FileWriter fw = null;
        try {
            fw = new FileWriter("data.txt");
            fw.write("Hello");
            throw new IOException();
        } catch (IOException e) {
            System.out.println("IOException catch!");
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("IOException = " + e.getMessage());
            }
        }
    }

    private static void ioExceptionWithTryResource() {
        try (FileWriter fw = new FileWriter("data.txt")) {  // AutoClosable 을 구현한 클래스이어야함
            // throw new IOException();
        } catch (IOException e) {
            System.out.println("IOException = " + e.getMessage());
        }
    }
}
