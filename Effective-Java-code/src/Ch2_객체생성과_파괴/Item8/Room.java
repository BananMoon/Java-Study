package Ch2_객체생성과_파괴.Item8;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable {

    // clean 작업을 할 별도의 스레드 생성
    // 해당 클래스 내에서는 Room(바깥 객체)의 자원을 참조하면 안되므로 "static" 키워드!
    // -> 순환참조가 생겨 GC가 Room 인스턴스를 회수할 기회가 오지 않아 정리 작업이 이루어지지 않는다.
    private static class RoomCleaner implements Runnable {
        // run 메서드 내에서 정리 작업이 이루어져야 함.
        // 1. Room의 close()메소드 호출  2. GC가 Room 회수할 때까지 client가 close호출 안할 시, cleaner가 호출
        @Override
        public void run() {
            System.out.println("방 청소");
            numTrash = 0;   // 자원 회수
        }

        int numTrash;   // 방(room) 안 쓰레기 수
        RoomCleaner(int numTrash) {
            this.numTrash = numTrash;
        }
    }

    // static 메서드인 create()로 (안전망으로 사용할) CLEANER 객체 생성
    private static final Cleaner CLEANER = Cleaner.create();

    // cleanable과 공유할 방의 상태 인스턴스 선언
    private final RoomCleaner roomCleaner;

    // CLEANER를 직접 쓰는게 아닌 Cleanable 인터페이스로 인스턴스 선언
    private final Cleaner.Cleanable cleanable;

    // 생성자
    public Room(int numTrash) {
        roomCleaner = new RoomCleaner(numTrash);
        cleanable= CLEANER.register(this, roomCleaner);  // Room과 roomCleaner를 등록해서 Cleanable 인스턴스 등록
    }

    @Override
    public void close() throws Exception {
        // AutoCloseable 인터페이스의 추상메서드 clean() 호출 -> clean작업하는 클래스의 run() 호출
        cleanable.clean();
    }
}

// 1. 클라이언트가 자원을 사용한 후 Room의 close() 호출 -> cleanable의 clean() 호출 -> roomCleaner의 run() 호출 -> 회수
// 2. 클라이언트가 close() 호출 X, GC가 cleaner가 roomCleaner의 run() 호출 -> 회수