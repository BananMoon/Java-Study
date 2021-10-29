//package Item3;
//
//import static org.junit.jupiter.api.Assertions;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Constructor;
//
//@Test
//@DisplayName("인스턴스가 전체 시스템에서 하나뿐임을 보장한다.")
//class Singleton1Test {
//    Singleton1 instance1 = Singleton1.INSTANCE;
//    Singleton1 instance2 = Singleton1.INSTANCE;
//
//    //단정(assert) 메소드: 테스트 케이스의 수행 결과를 판별
//    //assertSame(ox, oy): ox와 oy가 같은 객체를 참조하고 있으면(레퍼런스 동일?) 테스트 통과
//    Assertions.assertSame(instance1, instance2);
//}
