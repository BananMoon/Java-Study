package ch14.Stream;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class StudStream_Ex3 {
    public static void main(String[] args) {
        Student[] student = {
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        };
        Stream<Student> studentStream  = Stream.of(student);
        studentStream.sorted(Comparator.comparing(Student::getBan)         // 반별 정렬
                .thenComparing(Comparator.naturalOrder()))       // 내림차순 정렬
                .forEach(System.out::println);

        // 스트림을 다시 생성해서 IntStream의 메서드 사용해보자.
        studentStream = Stream.of(student);
        IntStream stuScoreStream = studentStream.mapToInt(Student::getTotalScore);
//왜 에러?
//        IntSummaryStatistics stat = studentStream.summaryStatistics();
    }
}

class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;
    Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore);
    }

    public String getName() { return name; }
    public int getBan() { return ban; }
    public int getTotalScore() { return totalScore; }

    // 기본정렬 : a.compareTo(b) : b-a이므로 a가 크면 음수, b가 크면 양수, 같으면 0)
    @Override
    public int compareTo(Student o) {
        return o.totalScore - this.totalScore;
    }
}