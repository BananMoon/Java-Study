package Item8;

public class SampleRunner {
    public static void main(String[] args) throws Exception {
        /* 자바 7부터는 try-with-resources를 사용하여 암묵적으로 close 호출 */
        try( SampleResource sampleResource = new SampleResource()) {
            sampleResource.hello();
        }   //따로 close()를 호출하지 않아도 try 블록이 끝날때 AutoCloseable 인터페이스에 있는 close()를 호출

        /* 기존에는 close() 메서드 호출을 보장하기위해 try-finally 사용
        SampleResource sampleResource = null;
        try {
            sampleResource = new SampleResource();
            sampleResource.hello();
        } finally {
            if (sampleResource != null) {   //값이 비지 않았으면
                sampleResource.close(); // 2. "클라이언트"에서 인스턴스를 다 쓰고나면 close 메서드 호출
            }
        }
        */
    }
}