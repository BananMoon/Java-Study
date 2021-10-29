package Item2;
//BuilderPattern 2-4

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {  //제한된 타입 파라미터 : 타입 파라미터에 지정되는 구체적 타입 제한
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);  //Topping이라는 enum다루는 빈 Set 반환
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));  // EnumSet에 topping이 안비어있으면 추가
            return self();  //self() 메서드 호출?
        }
        abstract Pizza build();

        protected abstract T self();    //하위 클래스에서 this 반환해야함
    }
    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
