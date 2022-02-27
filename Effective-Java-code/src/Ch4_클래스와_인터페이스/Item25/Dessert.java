package Ch4_클래스와_인터페이스.Item25;

//public class Dessert {
//    static final String NAME = "pot";
//}
//class Utensil {
//    static final String NAME = "pie";
//}

/* 원리
 javac MultiMain.java Dessert.java 로 컴파일을 실행하면
  MultiMain을 먼저 실행해줄 테니 , 그 안에서 Utensil과 Dessert를 참조하게 되는데, Utensil이 먼저 있으니 이를 참조하면 Utensil과 Dessert가 정의된 것을 확인하게 되고
 그 후 Dessert.java를 컴파일할 때 이미 정의된 클래스임을 알게 된다.
 */
