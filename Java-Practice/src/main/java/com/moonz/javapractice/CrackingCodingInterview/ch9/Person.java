package com.moonz.javapractice.CrackingCodingInterview.ch9;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class Person {
    private final List<Integer> friends = new ArrayList<>();
    @Getter
    private final Integer ID;
    @Getter
    private String name;

    public Person(int id) {
        ID = id;
    }

    public void addFriend (int id) {
        friends.add(id);
    }
}
