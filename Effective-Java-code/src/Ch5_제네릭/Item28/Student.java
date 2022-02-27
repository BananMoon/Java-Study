package Ch5_제네릭.Item28;

public class Student {
    private String firstName;
    private int studentNum;

    public Student(String firstName, int studentNum) {
        super();
        this.firstName = firstName;
        this.studentNum = studentNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getStudentNum() {
        return studentNum;
    }
}