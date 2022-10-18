package com.moonz.javapractice.CrackingCodingInterview.ch7.prob12;

public class Test {
    public static void main(String[] args) {
        PersonInfo ppl1 = new PersonInfo("Anna", 18);
        PersonInfo ppl2 = new PersonInfo("Hexa", 19);
        PersonInfo ppl3 = new PersonInfo("Poo", 20);
        PersonInfo ppl4 = new PersonInfo("Delta", 21);
        PersonInfo ppl5 = new PersonInfo("Vue", 22);
        PersonInfo ppl6 = new PersonInfo("Spring", 22);
        PersonInfo ppl7 = new PersonInfo("Anna", 24);   // need update
        PersonInfo[] arr = {ppl1, ppl2, ppl3, ppl4, ppl5, ppl6, ppl7};
        CustomHashTable<String, PersonInfo> people = new CustomHashTable<>(7);
        for (PersonInfo p : arr) {
            PersonInfo put = people.put(p.getName(), p);
            System.out.println("hashTable에 입력 : " + put.getName() + ", " + put.getAge()+ "살");
        }

        System.out.println("========전체 조회========");
        people.print();
        System.out.println("========Anna의 업데이트된 나이 : (18 -> "+ people.get("Anna").getAge() + ") ========");

        System.out.println("========이름이 Anna인 데이터 삭제========");
        System.out.println("삭제한 이름 : " + people.remove("Anna").getName());
        System.out.println("이름이 Anna인 데이터 (존재하지 않으면 null) : " + people.get("Anna"));
    }

}
