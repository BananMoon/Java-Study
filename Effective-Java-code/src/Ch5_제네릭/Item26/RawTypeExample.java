package Ch5_제네릭.Item26;

/**
 * 로타입은 사용하지 말자.
 *
 */
public class RawTypeExample<T> {
    private T member;

    public RawTypeExample(T member) {
        this.member = member;
    }

    public static void main(String[] args) {
        // 제네릭 파라미터 타입 사용
        RawTypeExample<Integer> parameterType = new RawTypeExample<>(1);
        Integer paramaeterTypeMember = parameterType.member;    //1
        System.out.println("제네릭 파라미터 타입 사용: " + paramaeterTypeMember);

        // 로 타입 사용
        RawTypeExample rawType = new RawTypeExample(1);
        Object rawTypeMember = rawType.member;
        System.out.println("로타입 사용: " + rawTypeMember);
    }
}
