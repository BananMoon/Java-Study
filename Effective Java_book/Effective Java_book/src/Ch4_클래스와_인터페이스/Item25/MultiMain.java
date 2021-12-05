package Ch4_클래스와_인터페이스.Item25;

public class MultiMain {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
}
/* 자바 컴파일 순서
1. 자바 소스 코드(.java) 작성
2. 자바 컴파일러가 자바 소스파일을 JVM용 바이트 코드(.class)로 컴파일
  - 아직 컴퓨터가 읽을 수 없는, 가상 머신이 이해할 수 있는 코드
3. 컴파일된 바이트 코드를 JVm의 클래스로더 에게 전달
4. 클래스로더는 동적 로딩 을 통해 필요한 클래스들을 로딩 및 연결하여 런타임 데이터 영역 (Runtime Data area), 즉 JVM의 메모리에 로드
  - 클래스로더 세부 동작 : 로드 - 검증 - 준비 - 분석 - 초기화
 */