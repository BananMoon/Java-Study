package Item6;

public class ImmutableHolder {
    private final Object value;
    public ImmutableHolder(Object o) {
        value = o;
    }
    public Object getValue() {
        return value;
    }

    public static void main(String[] args) {
        ImmutableHolder ins = new ImmutableHolder("s");
//        ins = "s";    //error : incompatible types: int cannot be converted to ImmutableHolder
        System.out.println(ins.getValue());
        System.out.println(2);

        final String name="Old";
//        name = "New";   //error: cannot assign a value to final variable name
        String s = new String("hello");
        String a = "hello";
        String b = "hello";
        System.out.println(s==a);   //false
        System.out.println(a==b);   //true
    }
}
