package Item10;

public class TestEnum {

    public static void main(String[] args) {
        // values(), ordinal() 메서드 사용
        EnumEx1[] values = EnumEx1.values();
        for (int i=0; i< values.length; i++) {
            System.out.println(values[i] +" 의 인덱스는: " + values[i].ordinal());
        }
        
        // valueOf() 메서드 사용
        EnumEx1 e = EnumEx1.valueOf("THREE");
        System.out.println(e);
    }
}
