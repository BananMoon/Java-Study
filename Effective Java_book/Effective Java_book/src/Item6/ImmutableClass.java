package Item6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableClass {
    private final int age;
    private final String name;
    private final List<String> people;

    private ImmutableClass(int age, String name) {
        this.age = age;
        this.name = name;
        this.people = new ArrayList<>();
    }

    public static ImmutableClass of (int age, String name) {
        System.out.println(age+ name);  //4yoon
        return new ImmutableClass(age, name);
    }

    public int getAge() { return age; }
    public String getName() { return name; }
    public List<String> getPeople() {
        return Collections.unmodifiableList(people);
    }
}