import java.io.Closeable;
import java.io.IOException;

/*
- try-with-resouce는 try 블록 안에서 선언된 객체가 try문이 종료되면 자동으로 자원을 해제해주는 기능이다. <-close()를 호출해주는 역할
- 단, 해당 객체가 AutoCloseable을 상속받고 있어야 하기 때문에,
  클래스가 try-with-resources으로 자원이 해제되길 원한다면 AutoCloseable을 implements해야 한다.
*/
public class try_with_resources {
    public static void main(String[] args) {
        try (CustomResource cr = new CustomResource()){
            cr.doSomething();
        } catch (Exception ex) {
        }
    }
}

class CustomResource implements Closeable {
    public void doSomething() {
        System.out.println("무언가를 한다.");
    }
    @Override
    public void close() throws IOException {
        System.out.println("CustomResource.close 호출!");
    }
}