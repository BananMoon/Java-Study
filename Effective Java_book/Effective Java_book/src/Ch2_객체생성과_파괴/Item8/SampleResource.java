package Ch2_객체생성과_파괴.Item8;

public class SampleResource implements AutoCloseable {  // 1. AutoCloseable 구현(implements)
    private boolean closed;  // 객체의 유효성 기록용 필드

    @Override
    public void close() throws Exception {  // 2. "클라이언트"에서 인스턴스를 다 쓰고나면 close 메서드 호출
        // 이 객체가 더이상 유효하지 않음을 필드 closed에 기록
        if (this.closed) {  // 객체가 이미 닫혔으면(true면)
            throw new IllegalAccessException(); // 에러 던지기
        }
        closed = true;  //안 닫혔으면 true로 표시하고 닫기
        System.out.println("close");
    }
    public void hello() {
        System.out.println("hi");
    }

    // 1. finalize를 안전망으로 사용. 만약 client가 close()를 호출하지 않은 경우
    @Override
    protected void finalize() throws Throwable {
        if (!this.closed) { // 객체가 안 닫혔으면(false면) close
            close();
        }
    }

}
